package com.bank.deal.entity;

import com.bank.deal.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "statement")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "statement_id")
    UUID statementId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    Credit credit;

    @Enumerated(EnumType.STRING)
    ApplicationStatus status;

    @Column(name = "creation_date")
    LocalDateTime creationDate;

    @Column(columnDefinition = "jsonb", name = "applied_offer")
    String appliedOffer;

    @Column(name = "sign_date")
    LocalDateTime signDate;

    @Column(name = "ses_code")
    String sesCode;

    @Column(columnDefinition = "jsonb", name = "status_history")
    String statusHistory;


}
