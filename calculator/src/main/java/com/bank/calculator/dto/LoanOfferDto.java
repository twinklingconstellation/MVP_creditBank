package com.bank.calculator.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanOfferDto {
     UUID statementId;
     BigDecimal requestedAmount;
     BigDecimal totalAmount; // Итоговая сумма с учетом всех условий
     Integer term; // Срок кредита
     BigDecimal monthlyPayment;
     BigDecimal rate; // Процентная ставка
     Boolean isInsuranceEnabled; // Страховка включена
     Boolean isSalaryClient; // Зарплатный клиент

}
