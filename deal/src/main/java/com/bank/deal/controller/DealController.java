package com.bank.deal.controller;

import com.bank.deal.dto.LoanOfferDto;
import com.bank.deal.dto.LoanStatementRequestDto;


import com.bank.deal.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deal")
public class DealController {
    @Autowired
    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping("/statement")
    public ResponseEntity<List<LoanOfferDto>> CalcPossibleLoanTerms(@RequestBody LoanStatementRequestDto requestDto) {
        List<LoanOfferDto> loanOffers = dealService.processLoanRequest(requestDto);
        return ResponseEntity.ok(loanOffers);

    }









}
