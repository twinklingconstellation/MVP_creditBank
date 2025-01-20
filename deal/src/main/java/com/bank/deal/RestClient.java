package com.bank.deal;

import com.bank.deal.dto.LoanOfferDto;
import com.bank.deal.dto.LoanStatementRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestClient {
    private RestTemplate restTemplate;

    @Value("${calculator.service.url}")
    private String serviceCalculateUrl;

    public List<LoanOfferDto> fetchLoanOffers(LoanStatementRequestDto requestDto) {
        ResponseEntity<List<LoanOfferDto>> response = restTemplate.exchange(
                serviceCalculateUrl + "/offers",
                HttpMethod.POST,
                new HttpEntity<>(requestDto),
                new ParameterizedTypeReference<List<LoanOfferDto>>() {
                });
        return response.getBody();
    }
}

