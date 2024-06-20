package com.i2i.cms.customexception;

/**
 * <p>
 * The StudentException class is a custom exception that extends the Exception class.
 * This custom exception is designed to handle specific error scenarios related to
 * student operations within the application.
 * </p>
 */
public class StudentException extends Exception {
    public StudentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

