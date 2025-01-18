package com.example.inveirl.admin.users.list;

import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class UsersListResponse {

    private UUID id;
    private String username;
    private String email;
    private RoleEnum role;
    private ZonedDateTime creationDate;
    private ZonedDateTime modificationDate;
}
