//package com.bank.deal.service;
//
//import com.bank.deal.RestClient;
//import com.bank.deal.dto.LoanOfferDto;
//import com.bank.deal.dto.LoanStatementRequestDto;
//import com.bank.deal.entity.Client;
//import com.bank.deal.entity.Passport;
//import com.bank.deal.entity.Statement;
//import com.bank.deal.repository.ClientRepository;
//import com.bank.deal.repository.StatementRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class LoanStatementService {
//    private final ClientRepository clientRepository;
//    private final StatementRepository statementRepository;
//    private final RestClient restClient;
//
//    private Client createClientFromRequest(LoanStatementRequestDto requestDto) {
//
//        Client client = new Client();
//        client.setFirstName(requestDto.getFirstName());
//        client.setLastName(requestDto.getLastName());
//        client.setMiddleName(requestDto.getMiddleName());
//        client.setEmail(requestDto.getEmail());
//        client.setBirthDate(requestDto.getBirthdate());
//
//        Passport passport = new Passport();
//        passport.setSeries(requestDto.getPassportSeries());
//        passport.setNumber(requestDto.getPassportNumber());
//        client.setPassport(passport);
//
//        return client;
//    }
//
//    private Statement createStatementFromRequest(Client client) {
//        Statement statement = new Statement();
//        statement.setClient(client);
//        statement.setCreationDate(LocalDateTime.now());
//        return statement;
//    }
//
//    public List<LoanOfferDto> handleLoanStatementRequest(LoanStatementRequestDto requestDto) {
//        Client client = createClientFromRequest(requestDto);
//        clientRepository.save(client);
//
//        Statement statement = createStatementFromRequest(client);
//        statementRepository.save(statement);
//
//        List<LoanOfferDto> loanOffers = restClient.fetchLoanOffers(requestDto);
//
//        loanOffers.forEach(loanOfferDto -> loanOfferDto.setStatementId(statement.getStatementId()));
//
//        return loanOffers;
//    }
//
//}
