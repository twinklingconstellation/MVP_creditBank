package com.bank.calculator.dto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmploymentDto {
     EmploymentStatus employmentStatus;
     String employerINN;
     BigDecimal salary;
     Position position;
     Integer workExperienceTotal;
     Integer workExperienceCurrent;
}
