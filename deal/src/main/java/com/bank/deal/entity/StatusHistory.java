package com.bank.deal.entity;

import com.bank.deal.enums.ApplicationStatus;
import com.bank.deal.enums.ChangeType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "status_history")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistory {
    @Id
    @GeneratedValue
    UUID id;

    @Enumerated(EnumType.STRING)
    ApplicationStatus status;

    LocalDateTime time;

    @Column(name = "change_type")
    @Enumerated(EnumType.STRING)
    ChangeType changeType;

}
