package com.cydeo.controller;

import com.cydeo.enums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.UUID;

@Controller
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /*
    write a method to return index.html including account list info
    endpoint:index
     */
    @GetMapping("/index")
    public String getIndexPage(Model model) {
        model.addAttribute("accountList", accountService.listAllAccount());
        return "account/index";

    }

    @GetMapping("/create-form")
    public String createCreateForm(Model model) {
        //we need to provide empty account object
        model.addAttribute("account", Account.builder().build()); //--> equal to new account, no Arg constructor
        //need to provide accountType enum for dropdown
        model.addAttribute("accountTypes", AccountType.values());

        return "account/create-account";
    }

    //create a method to capture information from UI
    //print them on the console
    //trigger createNewAccount method, create the account based on the user input
    //once user created return it back to the index page

    @PostMapping("/create")
    public String createAccount(@ModelAttribute("account") Account account){
        System.out.println(account);
        accountService.createNewAccount(account.getBalance(),new Date(),account.getAccountType(),account.getUserId());
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String getDeleteAccount(@PathVariable("id")UUID id){

        accountService.deleteAccount(id);

        return "redirect:/index";
    }

    @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable("id") UUID id){

        accountService.activateAccount(id);

        return "redirect:/index";
    }

    }


