package com.spring5app.animalclinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring5app.animalclinic.security.filter.JWTRequestFilter;

/* To do: How to disable SecurityConfigurer */

@EnableWebSecurity
public class SecurityConfigurer {
		
	 //Rest API Spring Security Configuration
	@Configuration
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public class APISecurity extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private UserDetailsService userService;
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userService);
		}

		@Autowired
		private JWTRequestFilter jwtRequestFilter;

		///animalclinic/api/authenticate
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().antMatcher("/animalclinic/api/**").authorizeRequests().antMatchers("/animalclinic/api/v1/owners")
					.permitAll().anyRequest().authenticated().and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		}
		
		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Bean
		public PasswordEncoder passWordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
	}
	
	@Configuration
	@Order(1)
	public class WebSecurity extends WebSecurityConfigurerAdapter
	{			
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/","/index", "/home","/signUp/**","/resources/**", "/webjars/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
		}
	}
}
