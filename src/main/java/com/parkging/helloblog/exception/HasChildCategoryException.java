package com.parkging.helloblog.exception;

public class HasChildCategoryException extends RuntimeException {
    public HasChildCategoryException() {
        super();
    }

    public HasChildCategoryException(String message) {
        super(message);
    }

    public HasChildCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public HasChildCategoryException(Throwable cause) {
        super(cause);
    }

    protected HasChildCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
