package com.bank.calculator.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data

public class ScoringDataDto {
    private BigDecimal amount; // Сумма кредита
    private Integer term; // Срок кредита
    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;
    private LocalDate birthdate;
    private String passportSeries;
    private String passportNumber;
    private LocalDate passportIssueDate; // Дата выдачи паспорта
    private String passportIssueBranch; // Кем выдан паспорт
    private MaritalStatus maritalStatus; // Семейное положение
    private Integer dependentAmount; // Количество иждивенцев
    private EmploymentDto employment; // Информация о занятости
    private String accountNumber; // Номер счета
    private Boolean isInsuranceEnabled; // Страховка включена
    private Boolean isSalaryClient; // Зарплатный клиент
}
