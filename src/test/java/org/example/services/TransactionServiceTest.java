package org.example.services;

import jakarta.persistence.EntityManager;
import org.example.models.Account;
import org.example.models.Transaction;
import org.example.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TransactionServiceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionService service;

    @Autowired
    TransactionFailureLogger log;

    @Test
    void successfulTransfer(){
        //Given account A with balance 1000.0 and account B with balance 500.0
        Account a = new Account("A", BigDecimal.valueOf(1000.0));
        Account b = new Account("B",BigDecimal.valueOf(500.0));

        List<Account> accounts = accountRepository.saveAll(List.of(a,b));


        //When Transfer
        Transaction newTransaction = service.transfer(accounts.getFirst().getId(),accounts.getLast().getId(),BigDecimal.valueOf(200));

        //Then
        assertEquals(BigDecimal.valueOf(800),accountRepository.findById(accounts.getFirst().getId()).get().getBalance());
        assertEquals(BigDecimal.valueOf(700),accountRepository.findById(accounts.getLast().getId()).get().getBalance());

        Optional<Transaction> fetchedTransaction = service.getTransactionFromID(newTransaction.getID());

        assertTrue(fetchedTransaction.isPresent());
        assertEquals("SUCCESS",fetchedTransaction.get().getStatus());
        assertEquals(a,entityManager.find(Account.class,accounts.getFirst().getId()));



    }







}