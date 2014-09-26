package com.seesideproductions.blinken.controllers;

/**
 * A DMX-related exception.
 *
 * @author scoleman
 * @since 0.0.1
 */
public class DMXException extends Exception
{
	private static final long serialVersionUID = 1L;

	public DMXException(String message)
    {
        super(message);
    }

    public DMXException(String message, Throwable reason)
    {
        super(message, reason);
    }
}