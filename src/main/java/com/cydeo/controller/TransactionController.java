package com.cydeo.controller;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class TransactionController {

   private final AccountService accountService;
   private final TransactionService transactionService;

    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }


    @GetMapping("/make-transfer")
    public String getMakeTransfer(Model model){

        //what we need to provide to make transfer happen
        //we need to provide empty transaction object
        model.addAttribute("transaction", Transaction.builder().build());
        //we need to provide list of all accounts
        model.addAttribute("accounts",accountService.listAllAccount());
        //need method from transaction service (list of last 10 transactions to fill the table --> business logic is missing)
        model.addAttribute("lastTransactions",transactionService.last10Transactions());

        return "transaction/make-transfer";
    }

    //write a post method that takes transaction object from the UI
    //complete the transfer and return the same page

    @PostMapping("/transfer")
    public String makeTransfer(@ModelAttribute("transaction") Transaction transaction){

        //I have UUID of accounts, but I need to provide Account object.
        //complete test conversion -> find accounts base on the ID and use as a param to complete make transfer method
        Account sender = accountService.retrieveByID(transaction.getSender());
        Account receiver = accountService.retrieveByID(transaction.getSender());


        transactionService.makeTransfer(sender,receiver,transaction.getAmount(), new Date(),transaction.getMessage());

        return "redirect:/make-transfer";
    }

}


