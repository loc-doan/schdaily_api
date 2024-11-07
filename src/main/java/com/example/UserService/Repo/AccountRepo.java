package com.example.UserService.Repo;

import com.example.UserService.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepo extends JpaRepository<Account,Integer> {
Account findAccountByUsername(String username);

}
