package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public PasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new JdbcUserDetailsManager();
//	}


	@Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
//				.userDetailsService(myDetailsService)
				.passwordEncoder(bcryptEncoder());
//        	.withUser("user").password("user").roles("ROLE");
    }
    
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated().and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.csrf().disable();
	}
	
}
