package com.parkging.blog.apiapp.global.config.jwt;


//todo 서버 기동 시 DB 혹은 환경변수에서 init 되도록 수정
public interface JwtProperties {
    String JWT_SECRET = "jwt-secret.blog.parkging.com";
    String REF_SECRET = "ref-secret.blog.parkging.com";
    Long JWT_EXPIRATION_MINUTE = 1L;
    Long REF_EXPIRATION_MINUTE = 10L;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String REFRESH_TOKEN_NAME = "refreshToken";
    String REFRESH_TOKEN_URL = "/silent-refresh";
    String JWT_USERNAME_PARAMETER = "email";



}
