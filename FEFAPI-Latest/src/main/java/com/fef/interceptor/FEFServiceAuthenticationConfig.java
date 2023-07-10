package com.fef.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Component
public class FEFServiceAuthenticationConfig implements WebMvcConfigurer
{

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
	registry.addInterceptor(authenticationInterceptor).addPathPatterns("/api/**");
    }

}
