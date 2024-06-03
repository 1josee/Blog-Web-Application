package com.tien.blogapplication.service;

import com.tien.blogapplication.config.JwtService;
import com.tien.blogapplication.entity.UserEntity;
import com.tien.blogapplication.mapper.UserMapper;
import com.tien.blogapplication.model.User;
import com.tien.blogapplication.repository.UserRepository;
import com.tien.blogapplication.request.AuthenticationRequest;
import com.tien.blogapplication.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(User user) {
        UserEntity userEntity = userMapper.toUserEntity(user);
        userRepository.save(userEntity);
        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user_name(userEntity.getUser_name())
                .role(userEntity.getRole().name())
                .build();
    }
}
