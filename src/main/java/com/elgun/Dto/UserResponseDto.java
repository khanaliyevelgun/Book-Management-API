package com.elgun.Dto;

import com.elgun.enumm.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {
    private String name;
    private String email;
    private UserRole userRole;
}
