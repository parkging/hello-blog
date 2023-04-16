package com.parkging.blog.apiapp.global.jwt;

public interface JwtProperties {
    Long JWT_EXPIRATION_MINUTE = 10L;
    Long REF_EXPIRATION_MINUTE = 180L;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String REFRESH_TOKEN_NAME = "refreshToken";
    String REFRESH_EXPIRE_TIME = "refreshExpireTime";
    String REFRESH_TOKEN_URL = "/silent-refresh";
    String JWT_USERNAME_PARAMETER = "email";
}
