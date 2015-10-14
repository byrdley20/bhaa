package com.coptertours.security;


//@Configuration
//@EnableWebMvcSecurity
public class WebSecurityConfig {// extends WebSecurityConfigurerAdapter {

//	private static final String USER_ROLE = "USER";
//	private static final String ADMIN_ROLE = "ADMIN";
//	private static final String MECHANIC_ROLE = "MECHANIC";
//
//	private static final String LOGIN_PAGE_URL = "http://flightlog.coptertours.com/login.html";
//	private static final String SUCCESSFUL_LOGIN_URL = "http://flightlog.coptertours.com";
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// http.csrf().disable();
//
//		// http.authorizeRequests()
//		// .antMatchers("/login.html").permitAll()
//		// .antMatchers("/admin/*.html").hasRole(ADMIN_ROLE)
//		// .antMatchers("/", "/*.html").hasAnyRole(USER_ROLE, ADMIN_ROLE, MECHANIC_ROLE)
//		// .anyRequest().authenticated()
//		// .and()
//		// .formLogin()
//		// .loginPage(LOGIN_PAGE_URL)
//		// .permitAll()
//		// .and()
//		// .logout()
//		// .logoutSuccessUrl("/");
//
//		// http.authorizeRequests()
//		// .antMatchers("/admin/**")
//		// .hasRole(ADMIN_ROLE)
//		// .and()
//		// .formLogin()
//		// .loginPage(LOGIN_PAGE_URL)
//		// // .loginPage("/login.html")
//		// .loginProcessingUrl("http://flightlog.coptertours.com/j_spring_security_check")
//		// // .defaultSuccessUrl(SUCCESSFUL_LOGIN_URL, true)
//		// .and()
//		// .logout()
//		// .logoutSuccessUrl("/");
//		//
//		// http.authorizeRequests()
//		// .antMatchers("/**")
//		// .hasAnyRole(USER_ROLE, ADMIN_ROLE, MECHANIC_ROLE)
//		// .and()
//		// .formLogin()
//		// .loginPage(LOGIN_PAGE_URL)
//		// .and()
//		// .logout()
//		// .logoutSuccessUrl("/");
//	}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("bhaa").password("coptertours1").roles(USER_ROLE);
//		auth.inMemoryAuthentication().withUser("admin").password("bell206").roles(ADMIN_ROLE);
//		auth.inMemoryAuthentication().withUser("mechanic").password("bhaamechanic").roles(MECHANIC_ROLE);
//	}
}