package com.zerostech.exception;

/**
 * This exception may be thrown by methods that a type is not be adapted.
 */
public class IncompatibilityException extends RuntimeException{

    public IncompatibilityException() {
    }

    public IncompatibilityException(String message) {
        super(message);
    }

    public IncompatibilityException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibilityException(Throwable cause) {
        super(cause);
    }

    public IncompatibilityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
