package com.elgun.controller;

import com.elgun.Dto.*;
import com.elgun.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "Users", description = "User management endpoints")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create user")
    public void addUser(@RequestBody @Valid UserRequestDto userRequestDto){
        userService.createUser(userRequestDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get book by ID")
    public UserResponseDto getUserById (@PathVariable @Min(1) Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all users")
    public Page<UserResponseDto> getAllUsers(@RequestParam(defaultValue = "0") @Min(0) int page,@RequestParam(defaultValue = "10") @Min(1) int size){
        return userService.getAllUsers(page,size);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user")
    public void deleteUser(@PathVariable @Min(1) Long id){
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update user")
    public void updateUser(@PathVariable @Min(1) Long id, @RequestBody @Valid UserUpdateDto userUpdateDto){
        userService.updateUser(id,userUpdateDto);
    }

}

