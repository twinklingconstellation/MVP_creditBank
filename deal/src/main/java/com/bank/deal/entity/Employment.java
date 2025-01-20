package com.bank.deal.entity;

import com.bank.deal.enums.EmploymentPosition;
import com.bank.deal.enums.EmploymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "employment")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Employment {
    @Id
    @GeneratedValue
    @Column(name = "employment_uuid")
    UUID employmentId;

    @Enumerated(EnumType.STRING)
    EmploymentStatus status;

    @Column(name = "employer_inn")
    String employerInn;

    BigDecimal salary;

    @Enumerated(EnumType.STRING)
    EmploymentPosition position;

    @Column(name = "work_experience_total")
    BigDecimal workExperienceTotal;

    @Column(name = "work_experience_current")
    BigDecimal workExperienceCurrent;

}
