package com.example.UserService.Controller;

import com.example.UserService.Dto.AuthRequest;
import com.example.UserService.Configua.JwtConf;
import com.example.UserService.Model.Account;
import com.example.UserService.Service.AccountService; // Sử dụng dịch vụ người dùng của bạn
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final JwtConf jwtConf;
    private final AccountService userDetailsService; // Thay đổi tên thành đúng lớp dịch vụ
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtConf jwtConf, AccountService userDetailsService, PasswordEncoder passwordEncoder) {

        this.jwtConf = jwtConf;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        // Lấy thông tin người dùng từ UserDetailsService
        Account userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("Invalid username or password");
        }

        // Kiểm tra mật khẩu
        if (passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())) {
            // Sinh JWT token nếu mật khẩu đúng
            return jwtConf.createToken(userDetails.getAccount_id());
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
    @GetMapping("/info")
    public int getUserInfo(@RequestHeader("Authorization") String token) {
        // Lấy token mà không có prefix "Bearer "
        String jwtToken = token.replace("Bearer ", "");

        int id = jwtConf.extractId(jwtToken);

        return id;
    }
    @GetMapping("/{id}")
    public Account getProByID(@PathVariable int id) {
        return userDetailsService.getUserByID(id);
    }
}
