package com.example.inveirl.infrastructure.auth.jwt;

import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class AuthenticatedUser {

    private UUID id;
    private String username;
    private String email;
    private RoleEnum role;
    private ZonedDateTime creationDate;
    private ZonedDateTime modificationDate;
}
