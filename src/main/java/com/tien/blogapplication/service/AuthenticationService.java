package com.tien.blogapplication.service;

import com.tien.blogapplication.model.User;
import com.tien.blogapplication.request.AuthenticationRequest;
import com.tien.blogapplication.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse register(User user);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
