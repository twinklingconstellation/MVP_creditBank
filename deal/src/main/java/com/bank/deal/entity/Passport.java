package com.bank.deal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "passport")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Passport {
    @Id
    @GeneratedValue
    @Column(name = "passport_uuid")
    UUID passportId;

    String series;

    String number;

    @Column(name = "issue_branch")
    String issueBranch;

    @Column(name = "issue_date")
    LocalDate issueDate;
}
