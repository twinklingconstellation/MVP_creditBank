package com.bank.calculator.dto;

import java.math.BigDecimal;
import lombok.Data;
import java.time.LocalDate;

@Data
public class LoanStatementRequestDto {
    private BigDecimal amount; // сумма кредита
    private Integer term; // срок кредита (месяц)
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private LocalDate birthdate;
    private String passportSeries;
    private String passportNumber;

    }
