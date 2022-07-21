package ru.itis.finalproject.exceptions;

import static ru.itis.finalproject.exceptions.MessageConstants.CONFIRM_CODE_NOT_FOUND;

public class ConfirmCodeNotFoundException extends ModelNotFoundException {
    public ConfirmCodeNotFoundException() {
        super(CONFIRM_CODE_NOT_FOUND);
    }
}
