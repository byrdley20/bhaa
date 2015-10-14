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

	private static final String LOGIN_PAGE_URL = "http://devflightlog.coptertours.com/login.html";
	private static final String SUCCESSFUL_LOGIN_URL = "http://devflightlog.coptertours.com";

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().disable();

		http.authorizeRequests().anyRequest().permitAll();

		// http.authorizeRequests()
		// .antMatchers("/login.html").permitAll()
		// .antMatchers("/admin/*.html").hasRole(ADMIN_ROLE)
		// .antMatchers("/", "/*.html").hasAnyRole(USER_ROLE, ADMIN_ROLE, MECHANIC_ROLE)
		// .anyRequest().authenticated()
		// .and()
		// .formLogin()
		// .loginPage(LOGIN_PAGE_URL)
		// .permitAll()
		// .and()
		// .logout()
		// .logoutSuccessUrl("/");

		// http.authorizeRequests()
		// .antMatchers("/login.html").permitAll()
		// .antMatchers("/admin/**")
		// .hasRole(ADMIN_ROLE)
		// .and()
		// .formLogin()
		// .loginPage(LOGIN_PAGE_URL).permitAll()
		// // .loginPage("/login.html")
		// .loginProcessingUrl(LOGIN_PAGE_URL).permitAll()
		// // .defaultSuccessUrl(SUCCESSFUL_LOGIN_URL, true)
		// .and()
		// .logout()
		// .logoutSuccessUrl("/");
		//
		// http.authorizeRequests()
		// .antMatchers("/**")
		// .hasAnyRole(USER_ROLE, ADMIN_ROLE, MECHANIC_ROLE)
		// .and()
		// .formLogin()
		// .loginPage(LOGIN_PAGE_URL)
		// .and()
		// .logout()
		// .logoutSuccessUrl("/");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("bhaa").password("coptertours1").roles(USER_ROLE);
		auth.inMemoryAuthentication().withUser("admin").password("bell206").roles(ADMIN_ROLE);
		auth.inMemoryAuthentication().withUser("mechanic").password("bhaamechanic").roles(MECHANIC_ROLE);
	}
}