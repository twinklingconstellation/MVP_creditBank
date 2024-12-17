package com.bank.calculator.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaymentScheduleElementDto {
    private Integer number; // Номер платежа
    private LocalDate date; // Дата платежа
    private BigDecimal totalPayment; // Общая сумма платежа
    private BigDecimal interestPayment; // Платеж по процентам
    private BigDecimal debtPayment; // Платеж по основной сумме долга
    private BigDecimal remainingDebt; // Остаток долга

}
