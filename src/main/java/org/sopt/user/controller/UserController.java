package org.sopt.user.controller;


import org.sopt.global.dto.ApiResponse;
import org.sopt.user.dto.UserRequestDto;
import org.sopt.user.dto.UserResponseDto;
import org.sopt.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto>> createPost(
            @RequestBody final UserRequestDto postRequestDto
    ) {
        UserResponseDto user = userService.createUser(postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(user));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllPosts(
            //    @RequestParam String keyword
    ) {
        List<UserResponseDto> posts = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.read(posts));
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getPostById(
            @PathVariable(name = "user-id") final Long id
    ) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.read(user));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> deletePostById(
            @PathVariable(name = "user-id") final Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.delete());
    }

    @PatchMapping("/{user-id}")
    public ResponseEntity<?> updatePost(
            @PathVariable(name = "user-id") final Long id,
            @RequestBody UserRequestDto postRequestDto
    ) {
        UserResponseDto post = userService.updateUser(id, postRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.update(post));
    }

    public UserService getUserService() {
        return userService;
    }
}
