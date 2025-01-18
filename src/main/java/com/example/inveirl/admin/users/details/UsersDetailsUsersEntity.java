package com.example.inveirl.admin.users.details;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "UsersDetailsUsersEntity")
class UsersDetailsUsersEntity {

    @Id
    @Column(updatable = false, insertable = false)
    private UUID id;

    @Column(updatable = false, insertable = false)
    private String username;

    @Column(updatable = false, insertable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private RoleEnum role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userId")
    private List<UsersDetailsRefreshTokensEntity> refreshTokens;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    public UsersDetailsResponse toUsersDetailsResponse() {
        return UsersDetailsResponse.builder()
                                   .id(id)
                                   .username(username)
                                   .email(email)
                                   .role(role)
                                   .refreshTokens(refreshTokens.stream()
                                                               .map(UsersDetailsRefreshTokensEntity::toRefreshTokens)
                                                               .toList())
                                   .creationDate(metadata.getCreationDate())
                                   .modificationDate(metadata.getModificationDate())
                                   .build();
    }
}
