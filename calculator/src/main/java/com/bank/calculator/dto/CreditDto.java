package com.bank.calculator.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;
@Schema(description = "Информация о кредите")
@Data
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