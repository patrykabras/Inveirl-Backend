package com.example.inveirl.admin.users.list;

import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@Schema(name = "UserListFilterRequest", description = "User list filter request")
class UserListFilterRequest {

    @Parameter(description = "User id", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;
    @Parameter(description = "Username contains", example = "user")
    private String usernameContains;
    @Parameter(description = "Email contains", example = "user@user.com")
    private String emailContains;
    @Parameter(description = "Roles", example = "[\"ADMIN\"]")
    private List<RoleEnum> roles;
}
