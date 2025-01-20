package com.bank.calculator.dto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinishRegistrationRequestDto {
     Gender gender;
     MaritalStatus maritalStatus;
     Integer dependentAmount; // Количество иждивенцев
     LocalDate passportIssueDate; // Дата выдачи паспорта
     String passportIssueBranch; // Кем выдан паспорт
     EmploymentDto employment; // Информация о занятости
     String accountNumber; // Номер счета
}
