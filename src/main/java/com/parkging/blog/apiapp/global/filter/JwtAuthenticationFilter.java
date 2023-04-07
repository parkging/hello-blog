package com.parkging.blog.apiapp.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkging.blog.apiapp.domain.member.domain.Member;
import com.parkging.blog.apiapp.global.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

// Spring Security에 UsernamePasswordAuthenticationFilter가 존재
// /login 요청 시 username, password 전송 (POST) 시 UsernamePasswordAuthenticationFilter 가 기본동작.
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login 요청 시 실행
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter.attemptAuthentication : 로그인 시도중");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        // 1. email, password 받아서
        // 2. 정상인지 로그인 시도 => authenticationManager 로 로그인 시도 시 PrincipalDetailsService 가 호출
        // 3. PrincipalDetailsService 에서 attemptAuthentication 메쏘드 호출
        // 4. PrincipalDetails를 세션에 담고 JWT 토큰 생성 후 응답
        try {
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            System.out.println("getEmail = " + member.getEmail());
            System.out.println("getPassword = " + bCryptPasswordEncoder.encode(member.getPassword()));

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());

            // PrincipalDetailsService의 loadUserByUsername() 가 실행 되면서 인증 실행
            // 인증 실패 => throw AuthenticationException(InternalAuthenticationServiceException | BadCredentialsException ...)
            // 인증 성공 => Authentication return
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("principal.getUsername() = " + principal.getUsername());

            //authentication 객체가 session영역에 저장됨.
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        return null;
    }

    // attemptAuthentication 실행 후 정상 인증인 경우 실행
    // JWT 토큰 생수 후 response
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("JwtAuthenticationFilter.successfulAuthentication : 인증 정상");
        super.successfulAuthentication(request, response, chain, authResult);
    }

    // attemptAuthentication 실행 후 인증 실패인 경우 실행
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("JwtAuthenticationFilter.unsuccessfulAuthentication : 인증 실패");
        log.error("", failed);
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
