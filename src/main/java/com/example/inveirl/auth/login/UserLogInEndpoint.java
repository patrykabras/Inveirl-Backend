package com.example.inveirl.auth.login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
class UserLogInEndpoint {
    private final UserLogInService service;

    @Tag(name = "User")
    @Operation(summary = "Login user", description = "POST methods that allow user to log in")
    @PostMapping("/auth/login")
    public UserLogInResponse userLogIn(@RequestBody final UserLogInRequest request) {
        return service.authenticate(request);
    }

    @Tag(name = "Admin")
    @Operation(summary = "Login admin", description = "POST methods that allow admin to log in")
    @PostMapping("/auth/admin/login")
    public UserLogInResponse adminLogIn(@RequestBody final UserLogInRequest request) {
        return service.authenticateAdmin(request);
    }
}