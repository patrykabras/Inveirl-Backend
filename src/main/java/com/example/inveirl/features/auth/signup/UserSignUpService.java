package com.example.inveirl.features.auth.signup;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UserSignUpService {
    private final UserSignUpUsersRepository userSignInRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSignUpUsersEntity signup(UserSignUpRequest request) {
        UserSignUpUsersEntity user = UserSignUpUsersEntity.builder()
                                                          .username(request.getUsername())
                                                          .email(request.getEmail())
                                                          .password(passwordEncoder.encode(request.getPassword()))
                                                          .build();


        return userSignInRepository.save(user);
    }
}