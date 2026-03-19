package com.elgun.service;

import com.elgun.dao.entity.User;
import com.elgun.dao.repository.UserRepository;
import com.elgun.Dto.UserRequestDto;
import com.elgun.Dto.UserResponseDto;
import com.elgun.Dto.UserUpdateDto;
import com.elgun.enumm.UserActive;
import com.elgun.fetcher.EntityFetch;
import com.elgun.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final EntityFetch entityFetch;
    @Override
    public void createUser(UserRequestDto userRequestDto) {
        userRepository.save(userMapper.mapRequestToEntity(userRequestDto));
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userMapper.mapEntityToResponseDto(entityFetch.fetchUserIfExists(id));
    }

    @Override
    public Page<UserResponseDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return userRepository.findAll(pageable).map(userMapper::mapEntityToResponseDto);
    }

    @Override
    public void updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = entityFetch.fetchUserIfExists(id);
        Optional.ofNullable(userUpdateDto.getEmail()).ifPresent(user::setEmail);
        Optional.ofNullable(userUpdateDto.getName()).ifPresent(user::setName);
        Optional.ofNullable(userUpdateDto.getPassword()).ifPresent(user::setPassword);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityFetch.fetchUserIfExists(id);
        user.setUserActive(UserActive.INACTIVE);
        userRepository.save(user);
    }

}
