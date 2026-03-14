package com.elgun.service;

import com.elgun.Dto.UserRequestDto;
import com.elgun.Dto.UserResponseDto;

public interface UserService {
    public void createUser(UserRequestDto userRequestDto);
    public UserResponseDto findUserById(Long id);
}
