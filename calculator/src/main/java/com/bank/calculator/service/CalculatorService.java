package com.bank.calculator.service;

import com.bank.calculator.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service; //фннотация спринга, которая говорит, что этот класс является сервисом

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

import static com.bank.calculator.dto.Gender.*;
import static com.bank.calculator.dto.Position.*;
import static com.bank.calculator.dto.MaritalStatus.*;

@Slf4j
@Service //отмечает класс как сервис
public class CalculatorService {

    //базовая процентная ставка
    private static final BigDecimal BASE_RATE = new BigDecimal("15");
    private static final String regularExpression = "[a-zA-Z]{2,30}";
    private static final String regularExpresForEmail = "^[a-z0-9A-Z_!#$%&'*+/=?`{|}~^.-]+@[a-z0-9A-Z.-]+$";

    public List<LoanOfferDto> generateLoanOffers(LoanStatementRequestDto request) {
        log.debug("Generating loan offers: {}", request);

        preScoringValidation(request);
        log.debug("Scoring completed successfully for request: {}", request);

        // Список для хранения кредитных предложений
        List<LoanOfferDto> offers = new ArrayList<>();

        // Генерация предложений на основе комбинаций isInsuranceEnabled и isSalaryClient
        for (boolean insurance : List.of(false, true)) {
            for (boolean salaryClient : List.of(false, true)) {

                LoanOfferDto offer = new LoanOfferDto();
                offer.setStatementId(UUID.randomUUID());
                offer.setRequestedAmount(request.getAmount());
                offer.setTerm(request.getTerm());

                // Базовая ставка
                BigDecimal rate = BASE_RATE;
                log.info("Generating new rate depending on the conditions, where start rate: {}", rate);
                if (insurance) rate = rate.subtract(new BigDecimal("3"));
                if (salaryClient) rate = rate.subtract(new BigDecimal("1"));
                offer.setRate(rate);
                log.info("New rate depending on the salary client or having insurance: {}", rate);

                // Общая сумма кредита и ежемесячный платеж
                BigDecimal totalAmount = request.getAmount();
                BigDecimal monthlyPayment = totalAmount.divide(
                        BigDecimal.valueOf(request.getTerm()), RoundingMode.UP);

                offer.setTotalAmount(totalAmount);
                offer.setMonthlyPayment(monthlyPayment);
                offer.setIsInsuranceEnabled(insurance);
                offer.setIsSalaryClient(salaryClient);

                offers.add(offer);
            }
        }
        // Сортировка предложений по ставке (от меньшей к большей)
        offers.sort(Comparator.comparing(LoanOfferDto::getRate));
        log.info("Generation loan offers completed successfully for request: {}", offers.size());
        return offers;

    }

    public CreditDto calculateCreditDetails(ScoringDataDto request) {

        log.debug("Calculating credit details: {}", request);

        BigDecimal rate = performScoring(request);
        log.info("Calculating credit rate  according to the scoring: {}", rate);

        try {
            CreditDto credit = new CreditDto();
            credit.setAmount(request.getAmount());
            credit.setTerm(request.getTerm());
            credit.setRate(rate);

            // Ежемесячный платеж и ПСК
            BigDecimal monthlyPayment = calculateMonthlyPayment(request.getAmount(), request.getTerm(), rate);
            credit.setMonthlyPayment(monthlyPayment);

            BigDecimal psk = monthlyPayment.multiply(BigDecimal.valueOf(request.getTerm()));
            credit.setPsk(psk);

            // График платежей
            List<PaymentScheduleElementDto> schedule = generatePaymentSchedule(request.getAmount(), rate, request.getTerm());
            credit.setPaymentSchedule(schedule);

            return credit;
        } catch (Exception e) {
            log.error("Calculating credit was failed: {}",e.getMessage());
        }
        return null;
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, int term, BigDecimal rate) {
        log.info("Calculating monthly payment for amount: amount - {}, term - {}", amount, term);
        BigDecimal monthlyRate = rate.divide(BigDecimal.valueOf(100), 4, RoundingMode.UP);
        return amount.multiply(monthlyRate).divide(
                BigDecimal.ONE.subtract(BigDecimal.ONE.divide(
                        BigDecimal.ONE.add(monthlyRate).pow(term), 4, RoundingMode.UP)), 2, RoundingMode.UP);
    }

    private List<PaymentScheduleElementDto> generatePaymentSchedule(BigDecimal amount, BigDecimal rate, int term) {

        log.debug("Generating payment schedule: {}", amount);

        List<PaymentScheduleElementDto> schedule = new ArrayList<>();
        BigDecimal remainingDebt = amount;
        BigDecimal monthlyPayment = calculateMonthlyPayment(amount, term, rate);

        for (int i = 1; i <= term; i++) {
            PaymentScheduleElementDto element = new PaymentScheduleElementDto();
            element.setNumber(i);
            element.setDate(LocalDate.now().plusMonths(i));
            element.setTotalPayment(monthlyPayment);
            element.setInterestPayment(monthlyPayment.multiply(new BigDecimal("0.2")));
            element.setDebtPayment(monthlyPayment.multiply(new BigDecimal("0.8")));
            remainingDebt = remainingDebt.subtract(element.getDebtPayment());
            element.setRemainingDebt(remainingDebt);
            schedule.add(element);
        }
        return schedule;
    }

    //можно было в отдельные классы вынести
    private void preScoringValidation(LoanStatementRequestDto request) {

        log.debug("Starting pre-scoring validation");

        if (!request.getFirstName().matches(regularExpression)
                || !request.getLastName().matches(regularExpression)
                || (request.getMiddleName() != null && !request.getMiddleName().matches(regularExpression))) {
            throw new IllegalArgumentException("Invalid name fields");
        }
        if (request.getAmount().compareTo(new BigDecimal("20000")) < 0) {
            throw new IllegalArgumentException("Loan amount must be at least 20000");
        }
        if (request.getTerm() < 6) {
            throw new IllegalArgumentException("Loan term must be at least 6 months");
        }
        if (request.getBirthdate().isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("User must be at least 18 years old");
        }
        if (!request.getEmail().matches(regularExpresForEmail)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!request.getPassportSeries().matches("\\d{4}") || !request.getPassportNumber().matches("\\d{6}")) {
            throw new IllegalArgumentException("Invalid passport data");
        }
    }

    private BigDecimal performScoring(ScoringDataDto request) {

        log.debug("Starting scoring for request");
        BigDecimal rate = BASE_RATE;

        // Возраст
        int age = LocalDate.now().getYear() - request.getBirthdate().getYear();
        if (age < 20 || age > 65) {
            throw new IllegalArgumentException("Age must be between 20 and 65 years");
        }

        // Пол
        if (FEMALE.equals(request.getGender()) && age >= 32 && age <= 60) {
            rate = rate.subtract(new BigDecimal("3"));
        } else if (MALE.equals(request.getGender()) && age >= 30 && age <= 55) {
            rate = rate.subtract(new BigDecimal("3"));
        } else if (UNKNOWN.equals(request.getGender())) {
            rate = rate.add(new BigDecimal("7"));
        }

        // Семейное положение
        if (MARRIED.equals(request.getMaritalStatus())) {
            rate = rate.subtract(new BigDecimal("3"));
        } else if (DIVORCED.equals(request.getMaritalStatus())) {
            rate = rate.add(new BigDecimal("1"));
        }

        // Рабочий статус
        switch (request.getEmployment().getEmploymentStatus()) {
            case UNEMPLOYED:
                throw new IllegalArgumentException("Unemployed applicants are not eligible");
            case SELF_EMPLOYED:
                rate = rate.add(new BigDecimal("2"));
                break;
            case BUSINESS_OWNER:
                rate = rate.add(new BigDecimal("1"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + request.getEmployment().getEmploymentStatus());
        }

        // Позиция на работе
        if (MID_MANAGER.equals(request.getEmployment().getPosition())) {
            rate = rate.subtract(new BigDecimal("2"));
        } else if (TOP_MANAGER.equals(request.getEmployment().getPosition())) {
            rate = rate.subtract(new BigDecimal("3"));
        }

        // Стаж работы
        if (request.getEmployment().getWorkExperienceTotal() < 5
                || request.getEmployment().getWorkExperienceCurrent() < 3) {
            throw new IllegalArgumentException("Insufficient work experience");
        }

        // Сумма займа и зарплата
        if (request.getAmount().compareTo(request.getEmployment().getSalary().multiply(new BigDecimal("24"))) > 0) {
            throw new IllegalArgumentException("Loan amount exceeds 24x salary");
        }

        return rate;
    }

}