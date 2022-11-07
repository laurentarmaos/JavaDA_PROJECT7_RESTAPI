package com.nnk.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nnk.springboot.services.UserService;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
        	.authorizeRequests()
        	//.anyRequest().permitAll();
        	.antMatchers("/register", "/webjars/**", "/css/**").permitAll()
        	.antMatchers("/user/add", "/user/validate", "/user/list", "/user/update").hasAuthority("ADMIN")
    		.anyRequest().authenticated()
    		.and()
    		.formLogin()
    			.loginPage("/login")
    				.permitAll()
    		.and()
    		.oauth2Login()
    			.loginPage("/login")
					.permitAll()
    		.and()
    		.exceptionHandling()
    		.accessDeniedPage("/403");
		return http.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
	@Autowired
	private UserService userService;
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();
    }
}
