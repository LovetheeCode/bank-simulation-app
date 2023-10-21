package com.cydeo.controller;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;

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
        Account receiver = accountService.retrieveByID(transaction.getReceiver());


        transactionService.makeTransfer(sender,receiver,transaction.getAmount(), new Date(),transaction.getMessage());

        return "redirect:/make-transfer";
    }
    //TASK
    //write a method that gets the account Id from index.html and print on the console
    //(work on index.html and here)
    //transaction/{id}
    //return transaction/transactions page

    @GetMapping("/transaction/{id}")
    public String getTransactionList(@PathVariable("id") UUID id, Model model){
        //print the id
        System.out.println(id);

        //get the list of transactions based on id and returns as a model attribute
        //TASK -> complete the method (service and repository)
        //findTransactionListById
        //add model, return to UI, getting result, display

        model.addAttribute("transactions", transactionService.findTransactionListById(id));

        return "transaction/transactions";
    }

    //go to transactions.html --> put based on the transaction "No transactions yet" or  Transactions table

}


