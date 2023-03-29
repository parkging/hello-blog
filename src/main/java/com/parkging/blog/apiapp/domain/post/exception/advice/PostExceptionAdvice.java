package com.parkging.blog.apiapp.domain.post.exception.advice;

import com.parkging.blog.apiapp.global.exception.ErrorMessageUtil;
import com.parkging.blog.apiapp.global.exception.ErrorResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.parkging.blog.apiapp.domain.post")
@RequiredArgsConstructor
@Slf4j
public class PostExceptionAdvice {

    private final ErrorMessageUtil eu;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ErrorResult illegalArgumentException(IllegalArgumentException e) {
        log.error("[PostExceptionAdvice.illegalArgumentException]", e);
        return eu.getErrorResult(e);
    }

}
