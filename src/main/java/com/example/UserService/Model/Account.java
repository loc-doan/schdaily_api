package com.example.UserService.Model;

import jakarta.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    public int account_id;

    @Column(name = "username")
    public String username;

    @Column(name = "password")
    public String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    public Role role;

    public Account() {}

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(int account_id, Role role, String password, String username) {
        this.account_id = account_id;
        this.role = role;
        this.password = password;
        this.username = username;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
