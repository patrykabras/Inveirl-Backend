package com.example.inveirl.auth.login;

public interface UserLogInFacade {

    UserLogInResponse authenticate(String email, String password);
}
