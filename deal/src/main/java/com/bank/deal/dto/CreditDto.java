package com.bank.deal.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreditDto {
    BigDecimal amount; // Сумма кредита
    Integer term; // Срок кредита
    BigDecimal monthlyPayment;
    BigDecimal rate;
    BigDecimal psk; // Полная стоимость кредита
    Boolean isInsuranceEnabled;
    Boolean isSalaryClient; // Зарплатный клиент
    List<PaymentScheduleElementDto> paymentSchedule;

}