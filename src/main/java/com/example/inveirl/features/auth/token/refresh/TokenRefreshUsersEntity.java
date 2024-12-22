package com.example.inveirl.features.auth.token.refresh;

import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
@Table(name = "users")
@Entity(name = "TokenRefreshUsersEntity")
class TokenRefreshUsersEntity implements UserDetails {

    @Id
    @Column(insertable = false, updatable = false)
    private UUID id;

    @Column(insertable = false, updatable = false)
    private String username;

    @Column(insertable = false, updatable = false)
    private String email;

    @Column(insertable = false, updatable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private RoleEnum role;

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

    public boolean isAdmin() {
        return RoleEnum.ADMIN.equals(role);
    }
}
