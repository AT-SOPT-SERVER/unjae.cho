package org.sopt.user.service;

import org.sopt.user.dto.UserRequestDto;
import org.sopt.user.dto.UserResponseDto;
import org.sopt.user.domain.User;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(
            UserRequestDto userRequestDto
    ) {
        User user = new User(userRequestDto.name());
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    public List<UserResponseDto> getAllUsers(
    ){
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public UserResponseDto getUserById(
            Long id
    ){
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDto(user);
    }

    public UserResponseDto updateUser(
            Long id,
            UserRequestDto userRequestDto
    ){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userRequestDto.name());
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    public void deleteUser(
            Long id
    ){
        userRepository.deleteById(id);
    }
}
