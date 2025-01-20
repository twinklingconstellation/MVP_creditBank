package com.bank.deal.service;

import com.bank.deal.RestClient;
import com.bank.deal.dto.LoanOfferDto;
import com.bank.deal.dto.LoanStatementRequestDto;
import com.bank.deal.entity.Client;
import com.bank.deal.entity.Statement;
import com.bank.deal.repository.ClientRepository;
import com.bank.deal.repository.StatementRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DealService {

    private ClientRepository clientRepository;
    private StatementRepository statementRepository;
    private RestClient restClient;

    public List<LoanOfferDto> processLoanRequest(LoanStatementRequestDto requestDto) {

        Client client = new Client();
        client.setFirstName(requestDto.getFirstName());
        client.setLastName(requestDto.getLastName());
        client.setMiddleName(requestDto.getMiddleName());
        client.setEmail(requestDto.getEmail());
        client.setBirthDate(requestDto.getBirthdate());
        clientRepository.save(client);

        Statement statement = new Statement();
        statement.setClient(client);
        statement.setCreationDate(LocalDateTime.now());
        statementRepository.save(statement);

        List<LoanOfferDto> loanOffers = restClient.fetchLoanOffers(requestDto);

        List<LoanOfferDto> updatedOffers = new ArrayList<>();
        for (LoanOfferDto loanOfferDto : loanOffers) {
            loanOfferDto.setStatementId(statement.getStatementId());
            updatedOffers.add(loanOfferDto);
        }
        return updatedOffers;

    }


}
