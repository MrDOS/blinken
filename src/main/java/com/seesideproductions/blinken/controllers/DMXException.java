package com.seesideproductions.blinken.controllers;

/**
 * A DMX-related exception.
 *
 * @author scoleman
 * @since 0.0.1
 */
public class DMXException extends Exception
{
    public DMXException(String message)
    {
        super(message);
    }

    public DMXException(String message, Throwable reason)
    {
        super(message, reason);
    }
}