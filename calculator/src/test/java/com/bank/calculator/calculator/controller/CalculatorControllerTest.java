package com.bank.calculator.calculator.controller;
import com.bank.calculator.dto.CreditDto;
import com.bank.calculator.dto.LoanOfferDto;
import com.bank.calculator.dto.ScoringDataDto;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import com.bank.calculator.controller.CalculatorController;
import com.bank.calculator.dto.LoanStatementRequestDto;
import com.bank.calculator.service.CalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@WebMvcTest(controllers = CalculatorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CalculatorControllerTest {
    @Mock
    private CalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;


     @BeforeEach
     void setUp() {
         MockitoAnnotations.openMocks(this);
         calculatorController = new CalculatorController(calculatorService);
     }

//     @Test
//     public void testCalculateLoanOffers_Success() throws Exception {
//         //given
//         LoanStatementRequestDto request = new LoanStatementRequestDto();
//         request.setAmount(new BigDecimal("20000"));
//         request.setTerm(23);
//         request.setBirthdate(LocalDate.of(1990, 2,2));
//         request.setFirstName("John");
//         request.setLastName("Smith");
//         request.setMiddleName("Wirenf");
//         request.setEmail("john.smith@gmail.com");
//         request.setPassportSeries("1212");
//         request.setPassportNumber("1234");
//
//         //then
//         List<LoanOfferDto> ResponseEntity = calculatorService.generateLoanOffers(request);
//         Assertions.assertNotNull(ResponseEntity);
//         Assertions.assertEquals(200, ResponseEntity);
//         Assertions.assertEquals(ResponseEntity.size(), 1);
//
//     }



    @Test
    public void testCalculateLoanOffersSuccess() throws Exception {
        LoanStatementRequestDto request = new LoanStatementRequestDto();
        request.setAmount(new BigDecimal("20000"));
        request.setTerm(23);
//        request.setBirthdate(LocalDate.of(1990, 2,2));
//        request.setFirstName("John");
//        request.setLastName("Smith");
//        request.setMiddleName("Wirenf");
//        request.setEmail("john.smith@gmail.com");
//        request.setPassportSeries("1212");
//        request.setPassportNumber("1234");


        LoanOfferDto offer = new LoanOfferDto();
        offer.setStatementId(UUID.randomUUID());
        offer.setRequestedAmount(request.getAmount());
        offer.setTerm(request.getTerm());
        offer.setRate(new BigDecimal("12"));
        offer.setTotalAmount(new BigDecimal("50000"));
        offer.setMonthlyPayment(new BigDecimal("4200"));
        offer.setIsInsuranceEnabled(true);
        offer.setIsSalaryClient(false);

//        LoanOfferDto offer1 = new LoanOfferDto();
//        LoanOfferDto offer2 = new LoanOfferDto();
//        LoanOfferDto offer3 = new LoanOfferDto();
//        LoanOfferDto offer4 = new LoanOfferDto();
//
//        List<LoanOfferDto> loanOffers = Arrays.asList(offer1, offer2, offer3, offer4);
        Mockito.when(calculatorService.generateLoanOffers(request))
                .thenReturn(List.of(offer));

        ResponseEntity<?> response = calculatorController.calculateLoanOffers(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());

        List<LoanOfferDto> responseBody = (List<LoanOfferDto>) response.getBody();
        Assertions.assertEquals(1, responseBody.size());

    }

    @Test
    void testCalculateCreditSuccess() {
        ScoringDataDto scoringDataDto = new ScoringDataDto();
        scoringDataDto.setAmount(new BigDecimal("100000"));
        scoringDataDto.setTerm(24);

        CreditDto creditDto = new CreditDto();
        creditDto.setAmount(new BigDecimal("100000"));
        creditDto.setTerm(24);
        creditDto.setRate(new BigDecimal("10"));
        creditDto.setMonthlyPayment(new BigDecimal("4500"));

        Mockito.when(calculatorService.calculateCreditDetails(scoringDataDto)).thenReturn(creditDto);

        ResponseEntity<?> response = calculatorController.calculateCredit(scoringDataDto);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        CreditDto result = (CreditDto) response.getBody();
        Assertions.assertEquals(new BigDecimal("100000"), result.getAmount());
    }

}
