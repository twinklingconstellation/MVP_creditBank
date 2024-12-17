package  com.bank.calculator.controller;

import com.bank.calculator.dto.*;

import com.bank.calculator.service.*; // аннотации спринга для работы с веб-запросами

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.List; //импорт для работы с коллекциями
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity; //для более гибкого управления HTTP-ответами



@RestController //указатель на то, что этот класс рест контроллер (для работы с HTTP-запросами

@RequestMapping("/calculator")
// класс для обработки запросов
public class CalculatorController {

    private final CalculatorService calculatorService;

    //создаем логгер
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);


    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    //передает данные нв сервер
    @PostMapping("/offers")
    /*
    @RequestBody Loan.. говорит, что тело запроса надо преобразовать
    в объект LoanStatementRequestDto
    и вернется список объектов LoanOfferDto

     */
    public ResponseEntity<List<LoanOfferDto>> calculateLoanOffers(@Valid @RequestBody LoanStatementRequestDto request) { // Здесь мы получаем LoanStatementRequestDto из тела запроса
        logger.info("Data for calculating loan offers was received: {}", request);

        try {

            // Логика для создания предложений кредита
            List<LoanOfferDto> offers = calculatorService.generateLoanOffers(request);
            logger.info("Data for loan offers was calculated: {}", offers);

            return ResponseEntity.ok(offers); // Возвращаем успешный ответ (HTTP 200 OK)
        } catch (IllegalArgumentException e) {
            logger.error("Error during preparing of loan offers: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during preparing loah offers", e);
            return ResponseEntity.status(500).build();
        }

        return null;
    }

    @PostMapping("/calc")

    public ResponseEntity<CreditDto> calculateCredit(@Valid @RequestBody ScoringDataDto request) {
        logger.info("Calculating credit: {}", request );

        try {
            // Расчёт параметров кредита через сервис
            CreditDto creditDetails = calculatorService.calculateCreditDetails(request);
            logger.info("Credit: {}", creditDetails);

            return ResponseEntity.ok(creditDetails); // Возвращаем успешный ответ (HTTP 200 OK)
        } catch (IllegalArgumentException e) {
            logger.error("Error during credit calculation: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during credit calculation", e);
            return ResponseEntity.status(500).build();
        }
        return null;
    }


    // Глобальный обработчик ошибок валидации
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (existingValue, newValue) -> existingValue)); // В случае дублирования полей используем первое значение
    }
}
