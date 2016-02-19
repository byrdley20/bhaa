package com.coptertours.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String USER_ROLE = "USER";
	private static final String ADMIN_ROLE = "ADMIN";
	private static final String MECHANIC_ROLE = "MECHANIC";

	private static final String LOGIN_PAGE_URL = "/login.html";
	private static final String SUCCESSFUL_LOGIN_URL = "http://devflightlog.coptertours.com";

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests()
				.antMatchers("/login.html").permitAll()
				.antMatchers("/admin/**").hasRole(ADMIN_ROLE)
				.antMatchers("/").permitAll()
				.and()
				.formLogin()
				.loginPage(LOGIN_PAGE_URL)
				.permitAll()
				.and()
				.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("bhaa").password("coptertours1").roles(USER_ROLE);
		auth.inMemoryAuthentication().withUser("admin").password("bell206").roles(ADMIN_ROLE);
		auth.inMemoryAuthentication().withUser("mechanic").password("bhaamechanic").roles(MECHANIC_ROLE);
	}
}