package org.sopt.user.controller;

import org.sopt.auth.annotation.UserId;
import org.sopt.global.dto.ApiResponse;
import org.sopt.user.dto.UserRequestDto;
import org.sopt.user.dto.UserResponseDto;
import org.sopt.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> createUser(
            @RequestBody final UserRequestDto userRequestDto
    ) {
        UserResponseDto user = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers(
            @UserId final Long userId
    ) {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.read(users));
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(
            @UserId final Long userId,
            @PathVariable(name = "user-id") final Long id
    ) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.read(user));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deleteUserById(
            @UserId final Long userId,
            @PathVariable(name = "user-id") final Long targetId
    ) {
        userService.deleteUser(userId, targetId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.delete());
    }
}
