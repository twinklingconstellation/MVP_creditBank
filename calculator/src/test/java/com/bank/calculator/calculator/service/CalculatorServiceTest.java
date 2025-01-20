package com.bank.calculator.calculator.service;

import com.bank.calculator.dto.*;
import com.bank.calculator.service.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

    @InjectMocks
    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void generateLoanOffers_ValidRequest_ReturnsOffers() {
        LoanStatementRequestDto request = new LoanStatementRequestDto();
        request.setAmount(new BigDecimal("20000"));
        request.setTerm(23);
        request.setBirthdate(LocalDate.of(1990, 2,2));
        request.setFirstName("Matti");
        request.setLastName("Rawn");
        request.setMiddleName("Wirenf");
        request.setEmail("mt.Rawn@gmail.com");
        request.setPassportSeries("1212");
        request.setPassportNumber("151215");

        List<LoanOfferDto> offers = calculatorService.generateLoanOffers(request);

        Assertions.assertNotNull(offers);
        Assertions.assertEquals(4, offers.size());
        Assertions.assertTrue(offers.get(0).getRate().compareTo(offers.get(1).getRate()) < 0);
    }
    @Test
    void calculateCreditDetails_ValidRequest_ReturnsCreditDetails() {
        // Arrange
        ScoringDataDto request = new ScoringDataDto();
        request.setAmount(new BigDecimal("100000"));
        request.setTerm(24);
        request.setBirthdate(LocalDate.of(1985, 5, 10));
        request.setGender(Gender.MALE);
        request.setMaritalStatus(MaritalStatus.MARRIED);

        EmploymentDto employment = new EmploymentDto();
        employment.setEmploymentStatus(EmploymentStatus.BUSINESS_OWNER);
        employment.setPosition(Position.MID_MANAGER);
        employment.setSalary(new BigDecimal("50000"));
        employment.setWorkExperienceTotal(10);
        employment.setWorkExperienceCurrent(5);
        request.setEmployment(employment);

        CreditDto creditDetails = calculatorService.calculateCreditDetails(request);

        Assertions.assertNotNull(creditDetails);
        Assertions.assertEquals(new BigDecimal("100000"), creditDetails.getAmount());
        Assertions.assertEquals(24, creditDetails.getTerm());
        Assertions.assertNotNull(creditDetails.getMonthlyPayment());
        Assertions.assertNotNull(creditDetails.getPsk());
        Assertions.assertNotNull(creditDetails.getPaymentSchedule());
    }

    @Test
    void generateLoanOffers_InvalidAmount_ThrowsException() {
        // Arrange
        LoanStatementRequestDto request = new LoanStatementRequestDto();
        request.setFirstName("Maty");
        request.setLastName("Westy");
        request.setMiddleName("Lyu");
        request.setAmount(new BigDecimal("10000")); // Сумма меньше минимальной
        request.setTerm(12);
        request.setBirthdate(LocalDate.of(1990, 1, 1));
        request.setEmail("maty.Lyu@gmail.com");
        request.setPassportSeries("1234");
        request.setPassportNumber("567890");

        // Act & Assert
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                calculatorService.generateLoanOffers(request));
        Assertions.assertEquals("Loan amount must be at least 20000", exception.getMessage());
    }

}
