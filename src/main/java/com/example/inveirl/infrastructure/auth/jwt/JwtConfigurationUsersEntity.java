package com.example.inveirl.infrastructure.auth.jwt;

import com.example.inveirl.infrastructure.Metadata;
import com.example.inveirl.infrastructure.enumeration.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "JwtConfigurationUsersEntity")
class JwtConfigurationUsersEntity implements UserDetails {

    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, insertable = false, updatable = false)
    private String username;

    @Column(nullable = false, insertable = false, updatable = false)
    private String email;

    @Column(nullable = false, insertable = false, updatable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, insertable = false, updatable = false)
    private RoleEnum role;

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
}
