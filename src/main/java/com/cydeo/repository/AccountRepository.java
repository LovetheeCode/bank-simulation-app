package com.cydeo.repository;

import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class AccountRepository {

    public static List<Account> accountList = new ArrayList<>();

    public Account save(Account account){
        accountList.add(account);
        return account;
    }

    public List<Account> findAll() {
        return accountList;
    }

    public Account findById(UUID id) {
        //it will find an account and return it to me
        //write a method that find account, that find the account inside the list, if not throw RecordNotFoundException
      return accountList.stream().filter(account -> account.getId().equals(id))
              .findAny()
              .orElseThrow(()-> new RecordNotFoundException("Account does not exist in the database."));




    }
}
