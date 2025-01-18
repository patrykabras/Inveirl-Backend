package com.example.inveirl.auth.signup;

import com.example.inveirl.auth.login.UserLogInFacade;
import com.example.inveirl.auth.login.UserLogInResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class SignUpService {
    private final SignUpUsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserLogInFacade userLogInFacade;


    public UserLogInResponse signup(SignUpRequest request) {
        SignUpUsersEntity user = SignUpUsersEntity.builder()
                                                  .username(request.getUsername())
                                                  .email(request.getEmail())
                                                  .password(passwordEncoder.encode(request.getPassword()))
                                                  .build();


        repository.saveAndFlush(user);
        return userLogInFacade.authenticate(request.getEmail(), request.getPassword());
    }
}