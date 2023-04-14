package com.parkging.blog.apiapp.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkging.blog.apiapp.domain.member.service.MemberService;
import com.parkging.blog.apiapp.global.config.cors.CorsConfig;
import com.parkging.blog.apiapp.global.config.jwt.JwtAuthenticationFilter;
import com.parkging.blog.apiapp.global.config.jwt.JwtAuthorizationFilter;
import com.parkging.blog.apiapp.global.config.jwt.JwtProperties;
import com.parkging.blog.apiapp.global.config.jwt.JwtRevalidationFilter;
import com.parkging.blog.apiapp.global.exception.ErrorMessageUtil;
import com.parkging.blog.apiapp.global.filter.ExceptionHandlerFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final ErrorMessageUtil errorMessageUtil;
    private final CorsConfig corsConfig;
    private final MemberService memberService;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //JWT 필터 추가
//        http.addFilterBefore(new JwtFilter(), SecurityContextPersistenceFilter.class);

        // 세션 미사용하므로 csrf 비활성화, Stateless 세팅
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new custumFilterDsl()) //커스텀 필터 적용
                .and()
                .authorizeRequests()
//                .antMatchers("/members/**").authenticated()  // 인증 되면 접속
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 인가 되면 접속
                .anyRequest().permitAll()

        ;
        return http.build();
    }

    public class custumFilterDsl extends AbstractHttpConfigurer<custumFilterDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity http) throws Exception {

            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, objectMapper);
            jwtAuthenticationFilter.setUsernameParameter(JwtProperties.JWT_USERNAME_PARAMETER);

            JwtRevalidationFilter jwtRevalidationFilter = new JwtRevalidationFilter(authenticationManager, memberService);
            jwtRevalidationFilter.setUsernameParameter(JwtProperties.JWT_USERNAME_PARAMETER);
            jwtRevalidationFilter.setFilterProcessesUrl(JwtProperties.REFRESH_TOKEN_URL);

            JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager, memberService);

            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(jwtAuthenticationFilter)
                    .addFilter(jwtRevalidationFilter)
                    .addFilter(jwtAuthorizationFilter)
                    .addFilterBefore(new ExceptionHandlerFilter(errorMessageUtil, memberService), UsernamePasswordAuthenticationFilter.class)
            ;
        }
    }

}
