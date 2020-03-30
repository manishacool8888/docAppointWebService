package com.docappoint.basic.auth;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.docappoint.constants.ApplicationConstants;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS,"/api/patient/**").hasRole(ApplicationConstants.PATIENT)
		.antMatchers(HttpMethod.OPTIONS,"/api/doctor/**").hasRole(ApplicationConstants.DOCTOR)
		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
			.anyRequest().authenticated()
			.and()
		//.formLogin().and()
		.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		   .antMatchers(HttpMethod.OPTIONS,"/api/public/**")
		   .antMatchers(HttpMethod.POST,"/api/public/**")
		   .antMatchers("/api/public/**");
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
