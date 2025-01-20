package com.bank.deal.dto;

import com.bank.deal.dto.EmploymentDto;
import com.bank.deal.enums.Gender;
import com.bank.deal.enums.MaritalStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
