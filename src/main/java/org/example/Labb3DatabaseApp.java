package org.example;

import org.example.models.Account;
import org.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class Labb3DatabaseApp implements CommandLineRunner {


    AccountRepository accRepo;

    public Labb3DatabaseApp(AccountRepository accRepo){
        this.accRepo = accRepo;

    }

    public static void main(String[] args) {
        SpringApplication.run(Labb3DatabaseApp.class, args);
    }

    @Override
    public void run(String... args){

        Account test = new Account("BigG", BigDecimal.valueOf(288220.0023));
        accRepo.save(test);

        List<Account> accountList = accRepo.findAll();
        accountList.forEach(System.out::println);


    }

}
