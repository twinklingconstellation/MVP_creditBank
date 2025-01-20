package com.bank.calculator.dto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatementStatusHistoryDto {
     StatementStatus status; // Статус заявки
     LocalDateTime time; // Время изменения
     ChangeType changeType; // Тип изменения
}
