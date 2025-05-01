package org.sopt.user.utils;

import org.sopt.user.exception.InvalidUserNameException;

public class UserValidator {

    static final int MAX_NAME_LENGTH = 10;

    public static void validateUser(String name) {
        if  (name == null || name.isBlank() || name.length() > MAX_NAME_LENGTH){
            throw new InvalidUserNameException();
        }
    }
}
