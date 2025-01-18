package com.example.inveirl.admin.users.list;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter(AccessLevel.PACKAGE)
@Builder(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "users")
@Entity(name = "UsersUsersEntity")
class UsersListUsersEntity {

    @Id
    @Column(nullable = false, updatable = false, insertable = false)
    private UUID id;

    @Column(nullable = false, updatable = false, insertable = false)
    private String username;

    @Column(unique = true, length = 100, nullable = false, updatable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, insertable = false, updatable = false)
    private RoleEnum role;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    public UsersListResponse toUsersListResponse() {
        return UsersListResponse.builder()
                                .id(id)
                                .username(username)
                                .email(email)
                                .role(role)
                                .creationDate(metadata.getCreationDate())
                                .modificationDate(metadata.getModificationDate())
                                .build();
    }
}
