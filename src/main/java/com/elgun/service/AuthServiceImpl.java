package com.elgun.service;

import com.elgun.auth.LoginRequestDto;
import com.elgun.dao.entity.User;
import com.elgun.dao.repository.UserRepository;
import com.elgun.exception.UserNotFoundException;
import com.elgun.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.elgun.constants.AppConstants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Override
    public String login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(()->new UserNotFoundException(USER_NOT_FOUND + loginRequestDto.getEmail()));
        return jwtUtil.generateToken(user.getEmail(),user.getUserRole().name());
    }
}
