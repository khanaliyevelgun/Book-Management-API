package com.elgun.service;

import com.elgun.auth.LoginRequestDto;

public interface AuthService {
    String login(LoginRequestDto loginRequestDto);
}
