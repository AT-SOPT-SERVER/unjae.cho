package org.sopt.user.service;

import org.sopt.user.dto.UserRequestDto;
import org.sopt.user.dto.UserResponseDto;
import org.sopt.user.domain.User;
import org.sopt.user.exception.UserNotFoundException;
import org.sopt.user.repository.UserRepository;
import org.sopt.user.utils.UserValidator;
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
        UserValidator.validateUser(userRequestDto.name());
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
                .orElseThrow(UserNotFoundException::new);
        return new UserResponseDto(user);
    }

    public UserResponseDto updateUser(
            Long id,
            UserRequestDto userRequestDto
    ){
        UserValidator.validateUser(userRequestDto.name());
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setName(userRequestDto.name());
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    public void deleteUser(
            Long id
    ){
        User user = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
