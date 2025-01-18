package com.example.inveirl.auth.signup;

import com.example.inveirl.auth.login.UserLogInFacade;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SignUpServiceTest {

    private final SignUpUsersRepository repository = mock(SignUpUsersRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final UserLogInFacade userLogInFacade = mock(UserLogInFacade.class);
    private final SignUpService service = new SignUpService(repository, passwordEncoder, userLogInFacade);

    @Test
    void shouldSignUpUser() {
        // given
        final ArgumentCaptor<SignUpUsersEntity> argument = ArgumentCaptor.forClass(SignUpUsersEntity.class);
        final String email = "user@user.com";
        final String password = "U$3r";
        final String encodedPassword = "encodedPassword";
        final String username = "User";

        final SignUpRequest request = SignUpRequest.builder()
                                                   .email(email)
                                                   .password(password)
                                                   .username(username)
                                                   .build();
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        // when
        service.signup(request);

        // then
        verify(repository).saveAndFlush(argument.capture());
        final SignUpUsersEntity savedEntity = argument.getValue();
        assertEquals(email, savedEntity.getEmail());
        assertEquals(encodedPassword, savedEntity.getPassword());
        assertEquals(username, savedEntity.getUsername());
        verify(userLogInFacade).authenticate(email, password);
    }

}