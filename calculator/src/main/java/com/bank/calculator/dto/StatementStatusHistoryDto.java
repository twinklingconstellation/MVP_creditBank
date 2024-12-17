package com.bank.calculator.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data

public class StatementStatusHistoryDto {
    private StatementStatus status; // Статус заявки
    private LocalDateTime time; // Время изменения
    private ChangeType changeType; // Тип изменения
}
