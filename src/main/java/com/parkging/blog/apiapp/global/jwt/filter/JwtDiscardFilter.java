package com.parkging.blog.apiapp.global.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkging.blog.apiapp.global.jwt.JwtProperties;
import com.parkging.blog.apiapp.global.jwt.util.JwtSecretKeyUtil;
import com.parkging.blog.apiapp.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * POST로 /logout url 요청 시 동작
 * /logout 요청 시 동작
 * JWT와, cookie에 refreshToken을 폐기해서 응답.
 */
@Slf4j
@RequiredArgsConstructor
public class JwtDiscardFilter extends UsernamePasswordAuthenticationFilter {
    //superClass에서 사용됨; 필수선언
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final JwtSecretKeyUtil jwtSecretKeyUtil;

    /**
     * Post로 "/logout" 요청 시 실행
     * @param request from which to extract parameters and perform the authentication
     * @param response the response, which may be needed if the implementation has to do a
     * redirect as part of a multi-stage authentication process (such as OpenID).
     * @return Authentication
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtDiscardFilter.attemptAuthentication");
        return JwtUtil.getAuthentication(null);
    }

    /**
     * attemptAuthentication 실행 후 정상 인증인 경우 실행; JWT 토큰 생성 후 response
     * @param request
     * @param response
     * @param chain
     * @param authResult the object returned from the <tt>attemptAuthentication</tt>
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("JwtDiscardFilter.successfulAuthentication : 인증 정상");

        String jwt = JwtUtil.getDiscardedJwtToken(jwtSecretKeyUtil.getJwtSecret());
        String refreshToken = JwtUtil.getDiscardedJwtToken(jwtSecretKeyUtil.getRefSecret());
        String refreshTokenCookie = JwtUtil.getDiscardedRefreshTokenCookie(refreshToken);
        String refreshTokenExpireTimeCookie = JwtUtil.getDiscardedRefreshTokenExpireTimeCookie();

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + "");
        response.setHeader("Set-Cookie", refreshTokenCookie);
        response.addHeader("Set-Cookie", refreshTokenExpireTimeCookie);
    }

    /**
     * attemptAuthentication 실행 후 인증 실패인 경우 실행
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("JwtDiscardFilter.unsuccessfulAuthentication : 인증 실패");
        log.info("", failed);
        throw failed;
    }
}
