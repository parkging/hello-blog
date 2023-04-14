package com.parkging.blog.apiapp.global.config.jwt;

import com.parkging.blog.apiapp.domain.member.domain.Member;
import com.parkging.blog.apiapp.domain.member.service.MemberService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 권한이 필요한 요청 시 인가를 위한 필터
 * JWT검증 후 시큐리티 세션에 authentication 객체 저장
 * MemberRole을 통하여 권한 검증
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private MemberService memberService;
    private JwtSecretKeyUtil jwtSecretKeyUtil;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberService memberService, JwtSecretKeyUtil jwtSecretKeyUtil) {
        super(authenticationManager);
        this.memberService = memberService;
        this.jwtSecretKeyUtil = jwtSecretKeyUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);

        // jwt header check
        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        String userEmail = JwtUtil.verifyToken(jwtToken, jwtSecretKeyUtil.getJwtSecret());

        // 토큰 서명이 정상적으로 이루어짐
        if(userEmail != null) {
            Member findMember = memberService.findByEmail(userEmail);
            Authentication authentication = JwtUtil.getAuthentication(findMember);

            // 시큐리티 세션에 강제로 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}
