package com.bank.deal.entity;

import com.bank.deal.enums.CreditStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "credit")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    @Id
    @GeneratedValue
    @Column(name = "credit_id_uuid")
    UUID creditId;

    BigDecimal amount;
    Integer term;

    @Column(name = "monthly_payment")
    BigDecimal monthlyPayment;

    BigDecimal rate;

    BigDecimal psk;

    @Column(columnDefinition = "jsonb", name = "payment_schedule")
    String paymentSchedule;

    @Column(name = "insurance_enabled")
    boolean insuranceEnabled;

    @Column(name = "salary_client")
    boolean salaryClient;

    @Column(name = "credit_status")
    @Enumerated(EnumType.STRING)
    CreditStatus creditStatus;


}
