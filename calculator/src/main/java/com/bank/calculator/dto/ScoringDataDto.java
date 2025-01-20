package com.bank.calculator.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScoringDataDto {
     BigDecimal amount; // Сумма кредита
     Integer term; // Срок кредита
     String firstName;
     String lastName;
     String middleName;
     Gender gender;
     LocalDate birthdate;
     String passportSeries;
     String passportNumber;
     LocalDate passportIssueDate; // Дата выдачи паспорта
     String passportIssueBranch; // Кем выдан паспорт
     MaritalStatus maritalStatus; // Семейное положение
     Integer dependentAmount; // Количество иждивенцев
     EmploymentDto employment; // Информация о занятости
     String accountNumber; // Номер счета
     Boolean isInsuranceEnabled; // Страховка включена
     Boolean isSalaryClient; // Зарплатный клиент
}
