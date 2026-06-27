package com.wcpredictor.exceptions;

public class TeamNotFoundException extends RuntimeException
{
    public TeamNotFoundException(final String message)
    {
        super(message);
    }
}
