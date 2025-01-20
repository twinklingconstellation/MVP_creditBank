package com.bank.calculator.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Schema(description = "Информация о пользователе")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanStatementRequestDto {
     BigDecimal amount; // сумма кредита
     Integer term; // срок кредита (месяц)
     String firstName;
     String lastName;
     String middleName;
     String email;
     LocalDate birthdate;
     String passportSeries;
     String passportNumber;


}