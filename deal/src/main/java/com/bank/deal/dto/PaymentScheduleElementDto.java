package com.bank.deal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentScheduleElementDto {

    Integer number; // Номер платежа
    LocalDate date; // Дата платежа
    BigDecimal totalPayment; // Общая сумма платежа
    BigDecimal interestPayment; // Платеж по процентам
    BigDecimal debtPayment; // Платеж по основной сумме долга
    BigDecimal remainingDebt; // Остаток долга
}
