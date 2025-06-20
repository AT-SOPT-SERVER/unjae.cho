package org.sopt.user.dto;

import lombok.Getter;
import org.sopt.user.domain.User;

@Getter
public class UserResponseDto
 {
     final Long id;
     final String name;

    public UserResponseDto(
            User user
    ){
        this.id = user.getId();
        this.name = user.getName();
    }
 }
