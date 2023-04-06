package com.parkging.blog.apiapp.global.config;

import com.parkging.blog.apiapp.global.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**
         * 구글 로그인 완료 후 후처리 =>
         * 1. 엑세스토큰 + 사용자 프로필정보,
         * 2. 회원가입 자동 진행
         */
        http.authorizeRequests()
                .antMatchers("/members/**").authenticated()  // 인증 되면 접속
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 인가 되면 접속
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .loginPage("/login")
                .loginProcessingUrl("/login") // /login 주소가 호출이 되면 시큐리티가 대신 낚아채서 로그인 진행
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
                ;
        return http.build();
    }

}
