package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration

// TODO-2 Make the below class to extend WebSecurityConfigurerAdapter
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// TODO-3 Uncomment the below to add httpBasic authentication for your app
	
	
	protected void configure(HttpSecurity http) throws Exception {		

		/*http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.httpBasic();*/
		
		http
		.authorizeRequests()
		.antMatchers("/register","/login","/h2-console/**","/mylogin","/verify/**").permitAll()
			.anyRequest().authenticated()
			.and()			
			.formLogin().loginPage("/login").defaultSuccessUrl("/hello", true)		
			.and().csrf().disable().rememberMe().key("myremembermekey")
			
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("remember-me");
		
		http.headers().frameOptions().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/webjars/**");
	}
	
	@Autowired
	private DataSource dataSource;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource).passwordEncoder(getPasswordEncoder());
	}	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		DelegatingPasswordEncoder encoder =  (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;	
	}
}
