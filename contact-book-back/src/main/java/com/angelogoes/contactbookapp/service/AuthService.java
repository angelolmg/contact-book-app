package com.angelogoes.contactbookapp.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.angelogoes.contactbookapp.dto.LoginRequest;
import com.angelogoes.contactbookapp.dto.RegisterRequest;
import com.angelogoes.contactbookapp.model.User;
import com.angelogoes.contactbookapp.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setLogin(registerRequest.getLogin());
        user.setEmail(registerRequest.getEmail());
        log.info(registerRequest.getPassword());
        log.info(String.valueOf(registerRequest.getPassword().length()));
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setData_cadastro(Instant.now());

        userRepository.save(user);

    }

    @Transactional(readOnly = true)
    public Boolean login(LoginRequest loginRequest) {
        String username = loginRequest.getLogin();
        Optional<User> userOptional = userRepository.findByLogin(username);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username:" + username));

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            return true;

        return false;
    }

}
