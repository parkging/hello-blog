package com.parkging.helloblog.exception;

public class NoCategoryException extends RuntimeException {
    public NoCategoryException() {
        super();
    }

    public NoCategoryException(String message) {
        super(message);
    }

    public NoCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCategoryException(Throwable cause) {
        super(cause);
    }

    protected NoCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
