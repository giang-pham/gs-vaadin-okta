package com.example.gsvaadinokta.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/login**")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable()
            .oauth2Login().defaultSuccessUrl("/userinfo");
    }
}



