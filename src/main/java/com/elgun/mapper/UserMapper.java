package com.elgun.mapper;
import com.elgun.dao.entity.User;
import com.elgun.Dto.UserRequestDto;
import com.elgun.Dto.UserResponseDto;
import com.elgun.enumm.UserActive;
import com.elgun.enumm.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public UserResponseDto mapEntityToResponseDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .userRole(user.getUserRole())
                .build();
    }
    public User mapRequestToEntity(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .email(userRequestDto.getEmail())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .userActive(UserActive.ACTIVE)
                .userRole(UserRole.USER)
                .build();
    }
}
