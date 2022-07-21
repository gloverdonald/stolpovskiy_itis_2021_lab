package ru.itis.finalproject.exceptions;

import static ru.itis.finalproject.exceptions.MessageConstants.ROLE_NOT_FOUND;

public class RoleNotFoundException extends ModelNotFoundException {

    public RoleNotFoundException() {
        super(ROLE_NOT_FOUND);
    }
}
