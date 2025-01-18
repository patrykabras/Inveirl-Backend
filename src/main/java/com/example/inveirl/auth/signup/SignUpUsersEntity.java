package com.example.inveirl.auth.signup;

import com.example.inveirl.infrastructure.Metadata;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "SignUpUsersEntity")
class SignUpUsersEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private String username;

    @Column(unique = true, length = 100, nullable = false, updatable = false)
    private String email;

    @Column(nullable = false, updatable = false)
    private String password;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    @PrePersist
    private void prePersist() {
        id = UUID.randomUUID();
    }
}
