package com.tien.blogapplication.mapper;

import com.tien.blogapplication.entity.RoleEntity;
import com.tien.blogapplication.entity.UserEntity;
import com.tien.blogapplication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserEntity toUserEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setUser_name(user.getUser_name());
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(RoleEntity.ROLE_USER);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        return userEntity;
    }
}
