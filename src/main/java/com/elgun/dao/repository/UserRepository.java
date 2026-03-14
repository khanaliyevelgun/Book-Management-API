package com.elgun.dao.repository;

import com.elgun.Dto.UserResponseDto;
import com.elgun.dao.entity.User;
import com.elgun.dao.entity.UserActive;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
}
