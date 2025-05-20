package org.sopt.user.dto;

import org.sopt.user.domain.User;

public class UserResponseDto
 {
     Long id;
     String name;

    public UserResponseDto(
            User user
    ){
        this.id = user.getId();
        this.name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
