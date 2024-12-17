package com.bank.calculator.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CreditDto {
    private BigDecimal amount; // Сумма кредита
    private Integer term; // Срок кредита
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private BigDecimal psk; // Полная стоимость кредита
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient; // Зарплатный клиент
    private List<PaymentScheduleElementDto> paymentSchedule;

}