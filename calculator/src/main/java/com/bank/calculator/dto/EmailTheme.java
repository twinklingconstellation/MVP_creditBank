package com.bank.calculator.dto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailTheme {
     String address; // Адрес электронной почты
     MailTheme theme; // Тема письма
     Long statementId; // ID заявки
     String text; // Текст письма

}
