package com.example.inveirl.features.auth.signup;

import com.example.inveirl.features.auth.login.UserLogInFacade;
import com.example.inveirl.features.auth.login.UserLogInResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserSignUpService {
    private final UserSignUpUsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserLogInFacade userLogInFacade;


    public UserLogInResponse signup(UserSignUpRequest request) {
        UserSignUpUsersEntity user = UserSignUpUsersEntity.builder()
                                                          .username(request.getUsername())
                                                          .email(request.getEmail())
                                                          .password(passwordEncoder.encode(request.getPassword()))
                                                          .build();


        repository.saveAndFlush(user);
        return userLogInFacade.authenticate(request.getEmail(), request.getPassword());
    }
}