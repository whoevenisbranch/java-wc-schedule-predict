package com.wcpredictor.exceptions;

public class UserExitedException extends RuntimeException {

    public UserExitedException(final String message)
    {
        super(message);
    }
}
