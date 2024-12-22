package com.example.inveirl.features.auth.signup;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserSignUpServiceTest {

    private final UserSignUpUsersRepository repository = mock(UserSignUpUsersRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final UserSignUpService service = new UserSignUpService(repository, passwordEncoder);

    @Test
    void shouldSignUpUser() {
        // given
        final ArgumentCaptor<UserSignUpUsersEntity> argument = ArgumentCaptor.forClass(UserSignUpUsersEntity.class);
        final String email = "user@user.com";
        final String password = "U$3r";
        final String encodedPassword = "encodedPassword";
        final String username = "User";

        final UserSignUpRequest request = UserSignUpRequest.builder()
                                                           .email(email)
                                                           .password(password)
                                                           .username(username)
                                                           .build();
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        // when
        service.signup(request);

        // then
        verify(repository).save(argument.capture());
        final UserSignUpUsersEntity savedEntity = argument.getValue();
        assertEquals(email, savedEntity.getEmail());
        assertEquals(encodedPassword, savedEntity.getPassword());
        assertEquals(username, savedEntity.getUsername());

    }

}