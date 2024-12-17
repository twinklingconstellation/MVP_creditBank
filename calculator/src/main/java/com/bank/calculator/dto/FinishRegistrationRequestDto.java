package com.bank.calculator.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class FinishRegistrationRequestDto {
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount; // Количество иждивенцев
    private LocalDate passportIssueDate; // Дата выдачи паспорта
    private String passportIssueBranch; // Кем выдан паспорт
    private EmploymentDto employment; // Информация о занятости
    private String accountNumber; // Номер счета
}
