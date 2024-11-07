package com.example.UserService.Controller;


import com.example.UserService.Model.Account;
import com.example.UserService.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ControllerAll {
    @Autowired
    private AccountService accountService;

    @PostMapping("/users/register")
    public Account register(@RequestBody Account newAcc) {
        return accountService.registerAccount(newAcc);
    }
    @GetMapping("/users")
    public List<Account> accountList() {
        return accountService.getAllAcc();
    }
}
