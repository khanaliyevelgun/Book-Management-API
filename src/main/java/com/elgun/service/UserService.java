package com.elgun.service;

import com.elgun.Dto.UserRequestDto;
import com.elgun.Dto.UserResponseDto;
import com.elgun.Dto.UserUpdateDto;
import org.springframework.data.domain.Page;

public interface UserService {
    void createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long id);
    Page<UserResponseDto> getAllUsers(int page,int size);
    void updateUser(Long id, UserUpdateDto userUpdateDto);
    void deleteUser(Long id);
}
