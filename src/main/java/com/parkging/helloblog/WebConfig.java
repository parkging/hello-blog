package com.parkging.helloblog;

import com.parkging.helloblog.resolver.UserHandlerExceptionResolver;
import com.parkging.helloblog.web.interceptor.LogInterceptor;
import com.parkging.helloblog.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new UserHandlerExceptionResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error", "/error/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/js/**", "/css/**", "/*.ico",
                        "/error", "/error/**",
                        "/boarder", "/boarder/*", "/login", "/logout",
                        "/signin", "/post", "/post/*", "/sidemenu-category"
                );
    }
}
