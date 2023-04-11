package com.parkging.blog.apiapp.global.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.parkging.blog.apiapp.domain.member.domain.Member;
import com.parkging.blog.apiapp.domain.member.service.MemberService;
import com.parkging.blog.apiapp.global.auth.PrincipalDetails;
import com.parkging.blog.apiapp.global.config.jwt.JwtProperties;
import com.parkging.blog.apiapp.global.config.jwt.JwtUtil;
import com.parkging.blog.apiapp.global.exception.ErrorMessageUtil;
import com.parkging.blog.apiapp.global.exception.ErrorResult;
import com.parkging.blog.apiapp.global.exception.RevalidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.parkging.blog.apiapp.global.config.jwt.JwtProperties.REFRESH_TOKEN_NAME;

@Slf4j
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final ErrorMessageUtil eu;
    private final MemberService memberService;
    private static final String LOGIN_ERROR_CODE = "error.login.fail";
    private static final String LOGIN_EXPIRED_CODE = "error.login.expired";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e){
            //인증 실패
            log.info("",e);;
            setLoginFailResult(LOGIN_ERROR_CODE, response);
        } catch (RevalidationException e) {
            //갱신 실패
            log.info("",e);;
            setLoginFailResult(LOGIN_EXPIRED_CODE, response);
        } catch (TokenExpiredException e) {
            log.info("",e);;
            //JWT 시간 만료; refreshToken 으로 JWT 갱신
            //갱신 실패
            setLoginFailResult(LOGIN_EXPIRED_CODE, response);

//            String refreshToken = JwtUtil.getRefreshTokenByCookies(request.getCookies());
//
//            //refreshToken 미존재 -> 재로그인 고지
//            if(refreshToken == null) {
//                setLoginFailResult(LOGIN_EXPIRED_CODE, response);
//                return;
//            }
//
//            try {
//                //refreshToken 검증
//                String userEmail = JwtUtil.verifyToken(refreshToken, JwtProperties.REF_SECRET);
//
//                // 토큰 서명이 정상적으로 이루어짐
//                if(userEmail != null) {
//                    Member findMember = memberService.findByEmail(userEmail);
//                    Authentication authentication = JwtUtil.getAuthentication(findMember);
//                    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//
//                    // 시큐리티 세션에 강제로 Authentication 객체 저장
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//                    String jwtToken = JwtUtil.getJwtToken(principalDetails);
//
//                    response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
//
//                    //
//                    filterChain.doFilter(request, response);
//                }
//
//            } catch (JWTVerificationException ex) {
//                log.info("JWT 갱신 실패", ex.getMessage());
//                setLoginFailResult(LOGIN_EXPIRED_CODE, response);
//                return;
//            }

        } catch (Exception e) {
            log.error("", e);
            ErrorResult errorResult = eu.getErrorResult(e);
            eu.setErrorResponse(response, errorResult);
        }

    }

    private void setLoginFailResult(String loginErrorCode, HttpServletResponse response) {
        ErrorResult errorResult = eu.getErrorResult(loginErrorCode, null, null);
        eu.setErrorResponse(response, errorResult);
    }
}
