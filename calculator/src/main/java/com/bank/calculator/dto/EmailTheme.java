package com.bank.calculator.dto;
import lombok.Data;

@Data
public class EmailTheme {
    private String address; // Адрес электронной почты
    private MailTheme theme; // Тема письма
    private Long statementId; // ID заявки
    private String text; // Текст письма

}
