package ru.itis.finalproject.exceptions;

import static ru.itis.finalproject.exceptions.MessageConstants.REVIEW_NOT_FOUND;

public class ReviewNotFoundException extends ModelNotFoundException {
    public ReviewNotFoundException() {
        super(REVIEW_NOT_FOUND);
    }
}
