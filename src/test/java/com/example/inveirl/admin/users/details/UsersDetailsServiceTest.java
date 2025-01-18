package com.example.inveirl.admin.users.details;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import com.example.inveirl.infrastructure.exceptions.UserNotFoundException;
import org.assertj.core.api.ThrowingConsumer;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsersDetailsServiceTest {

    private static final UUID USER_ID = UUID.randomUUID();
    private static final String USERNAME = "User";
    private static final String EMAIL = "user@user.com";
    private static final RoleEnum ROLE = RoleEnum.USER;
    private static final Metadata METADATA = new Metadata();

    private final UsersDetailsUsersRepository repository = mock(UsersDetailsUsersRepository.class);
    private final UsersDetailsService service = new UsersDetailsService(repository);

    @Test
    void shouldGetUserDetails() {
        // given
        final UsersDetailsUsersEntity entity = prepareUserEntity(Collections.emptyList());
        when(repository.findById(USER_ID)).thenReturn(Optional.ofNullable(entity));

        // when
        final UsersDetailsResponse response = service.getUserDetails(USER_ID);

        // then
        assertThat(response).isNotNull()
                            .satisfies(user -> {
                                assertThat(user.getId()).isEqualTo(USER_ID);
                                assertThat(user.getUsername()).isEqualTo(USERNAME);
                                assertThat(user.getEmail()).isEqualTo(EMAIL);
                                assertThat(user.getRole()).isEqualTo(ROLE);
                                assertThat(user.getCreationDate()).isEqualTo(METADATA.getCreationDate());
                                assertThat(user.getModificationDate()).isEqualTo(METADATA.getModificationDate());
                                assertThat(user.getRefreshTokens()).isEmpty();
                            });
    }

    @Test
    void shouldGetUserDetailsWithRefreshToken() {
        // given
        final List<UsersDetailsRefreshTokensEntity> refreshTokens = List.of(prepareRefreshToken());
        final UsersDetailsUsersEntity entity = prepareUserEntity(refreshTokens);

        when(repository.findById(USER_ID)).thenReturn(Optional.of(entity));

        // when
        final UsersDetailsResponse response = service.getUserDetails(USER_ID);

        // then
        assertThat(response).isNotNull()
                            .satisfies(user -> {
                                assertThat(user.getId()).isEqualTo(USER_ID);
                                assertThat(user.getUsername()).isEqualTo(USERNAME);
                                assertThat(user.getEmail()).isEqualTo(EMAIL);
                                assertThat(user.getRole()).isEqualTo(ROLE);
                                assertThat(user.getCreationDate()).isEqualTo(METADATA.getCreationDate());
                                assertThat(user.getModificationDate()).isEqualTo(METADATA.getModificationDate());
                                assertThat(user.getRefreshTokens()).isNotNull()
                                                                   .hasSize(1)
                                                                   .first()
                                                                   .satisfies(assertRefreshToken(refreshTokens));
                            });
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // given
        final UUID userId = UUID.randomUUID();
        when(repository.findById(userId)).thenReturn(Optional.empty());

        // when
        final var exception = assertThatThrownBy(() -> {
            service.getUserDetails(userId);
        });

        // then
        exception.isInstanceOf(UserNotFoundException.class)
                 .hasMessage("User with identifier '" + userId + "' not found")
                 .hasNoCause();
    }

    private static ThrowingConsumer<UsersDetailsResponse.RefreshTokens> assertRefreshToken(List<UsersDetailsRefreshTokensEntity> refreshTokens) {
        return refreshToken -> {
            assertThat(refreshToken.getId()).isEqualTo(refreshTokens.get(0)
                                                                    .getId());
            assertThat(refreshToken.getRefreshToken()).isEqualTo(refreshTokens.get(0)
                                                                              .getRefreshToken());
            assertThat(refreshToken.isActive()).isEqualTo(refreshTokens.get(0)
                                                                       .isActive());
            assertThat(refreshToken.getCreationDate()).isEqualTo(METADATA.getCreationDate());
            assertThat(refreshToken.getModificationDate()).isEqualTo(METADATA.getModificationDate());
        };
    }

    private static UsersDetailsUsersEntity prepareUserEntity(final List<UsersDetailsRefreshTokensEntity> refreshTokens) {
        return UsersDetailsUsersEntity.builder()
                                      .id(USER_ID)
                                      .username(USERNAME)
                                      .email(EMAIL)
                                      .role(ROLE)
                                      .refreshTokens(refreshTokens)
                                      .metadata(METADATA)
                                      .build();

    }

    private static UsersDetailsRefreshTokensEntity prepareRefreshToken() {
        return UsersDetailsRefreshTokensEntity.builder()
                                              .id(UUID.randomUUID())
                                              .userId(USER_ID)
                                              .refreshToken(UUID.randomUUID())
                                              .active(true)
                                              .metadata(METADATA)
                                              .build();
    }

}