package com.example.inveirl.admin.users.list;

import com.example.inveirl.infrastructure.enumeration.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Users")
class UsersListEndpoint {

    private final UsersListService service;

    @Operation(summary = "Get user list", description = "GET methods that provide user list")
    @GetMapping(value = "admin/users", produces = "application/json")
    @RolesAllowed(Role.ADMIN)
    public List<UsersListResponse> getUsers(final UserListFilterRequest filterRequest) {
        return service.allUsers(filterRequest);
    }
}