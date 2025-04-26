package io.com.github.pantry.api.service;

import io.com.github.pantry.api.dto.AuthResponse;
import io.com.github.pantry.api.dto.LoginRequest;
import io.com.github.pantry.api.dto.RegisterRequest;
import io.com.github.pantry.api.model.Account;
import io.com.github.pantry.api.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Account account = new Account();
        account.setEmail(request.getEmail());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole("USER");
        accountRepository.save(account);

        String jwt = jwtService.generateToken(account);

        return new AuthResponse(jwt);
    }

    public AuthResponse login(LoginRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), account.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String jwt = jwtService.generateToken(account);

        return new AuthResponse(jwt);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Account not found"));;

        return org.springframework.security.core.userdetails.User.builder()
                .username(account.getUserName())
                .password(account.getPassword())
                .roles("USER") // Pode ser "ADMIN" também, se quiser mais tarde
                .build();
    }
}

