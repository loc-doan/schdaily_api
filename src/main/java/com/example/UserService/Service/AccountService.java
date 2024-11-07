package com.example.UserService.Service;

import com.example.UserService.Model.Account;
import com.example.UserService.Model.Role;
import com.example.UserService.Repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService  {

    @Autowired
    private AccountRepo accountRepo;

    public Account loadUserByUsername(String username) {
        Account acc = accountRepo.findAccountByUsername(username);
        if (acc == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return acc;

    }
    public Account registerAccount(Account account) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        Role role = new Role();
        role.setId(2);
        role.setRole_name("user");
        account.setRole(role);
        return accountRepo.save(account);
    }

    public List<Account> getAllAcc() {
        return accountRepo.findAll();
    }
    public Account getUserByID(Integer id) {
        return accountRepo.findById(id).orElse(null);
    }
}
