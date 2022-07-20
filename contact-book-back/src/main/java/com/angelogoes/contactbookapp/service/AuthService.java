package com.angelogoes.contactbookapp.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.angelogoes.contactbookapp.dto.LoginRequest;
import com.angelogoes.contactbookapp.dto.RegisterRequest;
import com.angelogoes.contactbookapp.model.ContactBook;
import com.angelogoes.contactbookapp.model.User;
import com.angelogoes.contactbookapp.repository.ContactBookRepository;
import com.angelogoes.contactbookapp.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ContactBookRepository contactBookRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest) {

        User user = User.builder()
            .login(registerRequest.getLogin())
            .email(registerRequest.getEmail())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .data_cadastro(Instant.now())
            .build();

        ContactBook contactBook = ContactBook.builder()
            .user(user)
            .created(Instant.now())
            .build();

        user.setContactBook(contactBook);
        
        userRepository.save(user);
        contactBookRepository.save(contactBook);
    }

    @Transactional(readOnly = true)
    public Boolean login(LoginRequest loginRequest) {
        String username = loginRequest.getLogin();
        Optional<User> userOptional = userRepository.findByLogin(username);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com login:" + username));

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            return true;

        return false;
    }

}
