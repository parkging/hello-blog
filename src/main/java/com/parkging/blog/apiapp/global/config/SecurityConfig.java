package com.parkging.blog.apiapp.global.config;

import com.parkging.blog.apiapp.global.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

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
                .antMatchers("/members/**").authenticated()  // 인증 되면 접속
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 인가 되면 접속
                .anyRequest().permitAll()

        ;
        return http.build();
    }

    public class custumFilterDsl extends AbstractHttpConfigurer<custumFilterDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {

            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager);
            jwtAuthenticationFilter.setUsernameParameter("email");

            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
//                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository))
            ;
        }
    }

}
