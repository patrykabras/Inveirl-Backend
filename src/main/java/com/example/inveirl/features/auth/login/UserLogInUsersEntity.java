package com.example.inveirl.features.auth.login;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "UserLogInUsersEntity")
class UserLogInUsersEntity implements UserDetails {

    @Id
    @Column(nullable = false, updatable = false, insertable = false)
    private UUID id;

    @Column(nullable = false, updatable = false, insertable = false)
    private String username;

    @Column(nullable = false, updatable = false, insertable = false)
    private String email;

    @Column(nullable = false, updatable = false, insertable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, insertable = false)
    private RoleEnum role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userId")
    private List<UserLogInUserRefreshTokensEntity> refreshTokens;

    @Embedded
    @Builder.Default
    private Metadata metadata = new Metadata();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.name());

        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return username;
    }

    public void updateRefreshToken(UUID refreshToken) {
        final var newRefreshToken = UserLogInUserRefreshTokensEntity.builder()
                                                                    .refreshToken(refreshToken)
                                                                    .userId(id)
                                                                    .active(true)
                                                                    .build();
        this.refreshTokens.add(newRefreshToken);
    }

    public void validateIsAdmin() {
        if (!RoleEnum.ADMIN.equals(role)) {
            throw new BadCredentialsException("The username or password is incorrect");
        }
    }
}
