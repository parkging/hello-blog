package com.parkging.helloblog.exception;

public class NoParentCategoryException extends RuntimeException {
    public NoParentCategoryException() {
        super();
    }

    public NoParentCategoryException(String message) {
        super(message);
    }

    public NoParentCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoParentCategoryException(Throwable cause) {
        super(cause);
    }

    protected NoParentCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
