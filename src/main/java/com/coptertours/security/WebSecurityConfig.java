package com.coptertours.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http
	// .authorizeRequests()
	// .antMatchers("/", "/users").permitAll()
	// .anyRequest().authenticated()
	// .and()
	// .formLogin()
	// .loginPage("/login")
	// .permitAll()
	// .and()
	// .logout()
	// .permitAll();
	// }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/admin/**")
				.hasRole("ADMIN")
				// .authenticated()
				.and()
				.formLogin();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("bell206").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("mechanic").password("bhaamechanic").roles("MECHANIC");
	}
}