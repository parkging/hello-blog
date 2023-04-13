package com.parkging.blog.apiapp.global.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.parkging.blog.apiapp.domain.member.domain.Member;
import com.parkging.blog.apiapp.global.auth.PrincipalDetails;
import com.parkging.blog.apiapp.global.exception.RevalidationException;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.Cookie;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static com.parkging.blog.apiapp.global.config.jwt.JwtProperties.REFRESH_TOKEN_NAME;

public final class JwtUtil {

    public static String getJwtToken(PrincipalDetails principalDetails) {
        return JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(JwtProperties.JWT_EXPIRATION_MINUTE)))
                .withClaim("id", principalDetails.getId())
                .withClaim("email", principalDetails.getUsername())
                .withClaim("expire_millisecond", JwtProperties.JWT_EXPIRATION_MINUTE * 60 * 1000)
                .sign(Algorithm.HMAC512(JwtProperties.JWT_SECRET));
    }

    public static String getRefreshToken(PrincipalDetails principalDetails) {
        return JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(JwtProperties.REF_EXPIRATION_MINUTE)))
                .withClaim("id", principalDetails.getId())
                .withClaim("email", principalDetails.getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.REF_SECRET));
    }

    public static String getRefreshTokenByCookies(Cookie[] cookies) {
        if(cookies == null) throw new RevalidationException();
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(REFRESH_TOKEN_NAME))
                .findFirst()
                .orElseThrow(() -> new RevalidationException())
                .getValue();
    }

    public static String getRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from(JwtProperties.REFRESH_TOKEN_NAME, refreshToken)
                .maxAge(JwtProperties.REF_EXPIRATION_MINUTE * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build()
                .toString();
    }

    //리프레시토큰 만료시간 쿠키 생성(httpOnly 아님); 해당 쿠키로 클라이언트에서 리프레시토큰 여부 확인
    public static String getRefreshTokenExpireTimeCookie() {
        return ResponseCookie.from(JwtProperties.REFRESH_EXPIRE_TIME, Long.toString(JwtProperties.REF_EXPIRATION_MINUTE * 60))
                .maxAge(JwtProperties.REF_EXPIRATION_MINUTE * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(false)
                .build()
                .toString();
    }

    /**
     *
     * @param jwtToken
     * @param jwtSecret
     * @return String userEmail
     * @Throws JWTVerificationException (
                SignatureVerificationException – Sign 실패 시
                TokenExpiredException – 토큰 만료 시
                InvalidClaimException – claim 미존재 시
              )
     */
    public static String verifyToken(String jwtToken, String jwtSecret) {
        return JWT.require(Algorithm.HMAC512(jwtSecret)).build()
                .verify(jwtToken)
                .getClaim("email")
                .asString();
    }

    public static Authentication getAuthentication(Member member) {

        PrincipalDetails principalDetails = new PrincipalDetails(member);

        //Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체 생성
        return new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
    }
}
