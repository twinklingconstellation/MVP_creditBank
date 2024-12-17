package com.bank.calculator.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class LoanOfferDto {
    private UUID statementId;
    private BigDecimal requestedAmount;
    private BigDecimal totalAmount; // Итоговая сумма с учетом всех условий
    private Integer term; // Срок кредита
    private BigDecimal monthlyPayment;
    private BigDecimal rate; // Процентная ставка
    private Boolean isInsuranceEnabled; // Страховка включена
    private Boolean isSalaryClient; // Зарплатный клиент

}
