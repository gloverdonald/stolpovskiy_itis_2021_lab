package ru.itis.finalproject.exceptions;

import static ru.itis.finalproject.exceptions.MessageConstants.USER_NOT_FOUND;

public class UserNotFoundException extends ModelNotFoundException {

    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
