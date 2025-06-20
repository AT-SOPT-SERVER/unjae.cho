package org.sopt.user.service;

import org.sopt.global.exception.custom.UnauthorizedException;
import org.sopt.user.dto.UserRequestDto;
import org.sopt.user.dto.UserResponseDto;
import org.sopt.user.domain.User;
import org.sopt.global.exception.custom.UserNotFoundException;
import org.sopt.user.repository.UserRepository;
import org.sopt.user.utils.UserValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponseDto createUser(
            UserRequestDto userRequestDto
    ) {
        UserValidator.validateUser(userRequestDto.name());
        User user = new User(userRequestDto.name());
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers(
    ){
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserById(
            Long id
    ){
        User user = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
        return new UserResponseDto(user);
    }

    @Transactional
    public void deleteUser(
            Long userId,
            Long targetId
    ){
        User user = userRepository
                .findById(targetId)
                .orElseThrow(UserNotFoundException::new);
        if ((user.getId().equals(userId))) {
            userRepository.delete(user);
        }
        else {
            throw new UnauthorizedException();
        }
    }
}
