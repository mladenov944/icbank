package com.icbank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.icbank.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(PASSWORD_ENCODER);
	}

	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().sessionManagement().disable()// n1
				.authorizeRequests().antMatchers("/user-managment/register").permitAll() // n1
				.antMatchers("/swagger-ui.html/**").hasRole("ADMIN") // n1
				.antMatchers("/login/**").permitAll().and().formLogin() // n1
				.defaultSuccessUrl("/swagger-ui.html", true) // n1
				.failureUrl("/login.html?error=true") // n1
				.and().logout().logoutSuccessUrl("/login.html"); // n1

	}
}
