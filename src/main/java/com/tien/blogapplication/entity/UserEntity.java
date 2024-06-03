package com.tien.blogapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column (name = "user_name")
    private String user_name;
    @Column (name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEntity role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));

//        public List<SimpleGrantedAuthority> getAuthorities() {
//            var authorities = getPermissions()
//                    .stream()
//                    .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                    .collect(Collectors.toList());
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//            return authorities;
//        }
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
}
