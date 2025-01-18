package com.example.inveirl.admin.users.list;

import com.example.inveirl.AbstractSpringIntegrationTest;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/get_users_admin.sql")
class UsersListServiceIT extends AbstractSpringIntegrationTest {

    @Autowired
    private UsersListService service;

    @Test
    void getUsers() {
        // given
        final UserListFilterRequest request = UserListFilterRequest.builder()
                                                                   .build();

        // when
        final List<UsersListResponse> response = service.allUsers(request);

        // then
        assertThat(response).hasSize(2);
    }

    @Test
    void getUsersWithUserId() {
        // given
        final UUID userId = UUID.fromString("b2910f7d-ede9-472b-9fdf-4c82ee8c2619");
        final UserListFilterRequest request = UserListFilterRequest.builder()
                                                                   .id(userId)
                                                                   .build();

        // when
        final List<UsersListResponse> response = service.allUsers(request);

        // then
        assertThat(response).hasSize(1)
                            .first()
                            .extracting(UsersListResponse::getId)
                            .isEqualTo(userId);
    }

    @Test
    void getUsersWithUsernameContains() {
        // given
        final String usernameContains = "dmin";
        final UserListFilterRequest request = UserListFilterRequest.builder()
                                                                   .usernameContains(usernameContains)
                                                                   .build();

        // when
        final List<UsersListResponse> response = service.allUsers(request);

        // then
        assertThat(response).hasSize(1)
                            .first()
                            .extracting(UsersListResponse::getUsername)
                            .asString()
                            .contains(usernameContains);
    }

    @Test
    void getUsersWithEmailContains() {
        // given
        final String emailContains = "@user";
        final UserListFilterRequest request = UserListFilterRequest.builder()
                                                                   .emailContains(emailContains)
                                                                   .build();

        // when
        final List<UsersListResponse> response = service.allUsers(request);

        // then
        assertThat(response).hasSize(1)
                            .first()
                            .extracting(UsersListResponse::getEmail)
                            .asString()
                            .contains(emailContains);
    }

    @Test
    void getUsersWithRoleAdmin() {
        // given
        final UserListFilterRequest request = UserListFilterRequest.builder()
                                                                   .roles(List.of(RoleEnum.ADMIN))
                                                                   .build();

        // when
        final List<UsersListResponse> response = service.allUsers(request);

        // then
        assertThat(response).hasSize(1)
                            .first()
                            .extracting(UsersListResponse::getRole)
                            .isEqualTo(RoleEnum.ADMIN);
    }
}
