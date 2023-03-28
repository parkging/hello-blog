package com.parkging.blog.apiapp.global.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResult {
    private String code;
    private String message;
    private String redirectUrl;

    @Builder
    public ErrorResult(String code, String message, String redirectUrl) {
        this.code = code;
        this.message = message;
        this.redirectUrl = redirectUrl;
    }
}
