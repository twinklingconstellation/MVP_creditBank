package com.bank.deal.entity;

import com.bank.deal.enums.Gender;
import com.bank.deal.enums.MaritalStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue
    @Column(name = "client_id")
    UUID clientId;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "birth_date")
    LocalDate birthDate;

    String email;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    MaritalStatus maritalStatus;

    @Column(name = "dependent_amount")
    Integer dependentAmount;

    @OneToOne
    @JoinColumn(name = "passport_id")
    Passport passport;

    @OneToOne
    @JoinColumn(name = "employment_id")
    Employment employment;

    @Column(name = "account_number")
    String accountNumber;
}
