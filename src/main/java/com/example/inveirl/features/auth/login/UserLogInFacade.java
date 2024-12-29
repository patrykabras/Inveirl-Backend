package com.example.inveirl.features.auth.login;

public interface UserLogInFacade {

    UserLogInResponse authenticate(String email, String password);
}
