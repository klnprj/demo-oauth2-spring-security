package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    UserDetailsService myDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // userDetailsService should be changed to your user details service
        // password encoder being the bean defined in grails-app/conf/spring/resources.groovy
        auth.userDetailsService(myDetailsService)
                .passwordEncoder(bcryptEncoder);
    }
}