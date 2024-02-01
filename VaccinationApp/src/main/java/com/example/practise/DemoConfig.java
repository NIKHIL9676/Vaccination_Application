package com.example.practise;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.practise.entity.User;
import com.example.practise.service.UserService;

@Configuration
@EnableWebSecurity
public class DemoConfig {

	@Autowired
	UserService userService;
	
	@Bean
	public UserDetailsService users(DataSource dataSource) {
		
		JdbcUserDetailsManager jdbc = new JdbcUserDetailsManager(dataSource);
		
		jdbc.setUsersByUsernameQuery("select email,password, 1 as enabled from user where email=?");
		jdbc.setAuthoritiesByUsernameQuery("select email,role from user where email=?");
//		UserDetails user = User.builder().username("nikhil").password("{noop}test123").roles("EMPLOYEE").build();
//		UserDetails satish = User.builder().username("satish").password("{noop}test123").roles("EMPLOYEE", "ADMIN").build();
//		UserDetails hemanth = User.builder().username("hemanth").password("{noop}test123").roles("MANAGER").build();
//		return new InMemoryUserDetailsManager(user, satish,hemanth);
		return jdbc;
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> 
			configurer
				
				.requestMatchers("/updatePassword").permitAll()
				.requestMatchers("/sendOTP").permitAll()
				.requestMatchers("/forgottenPassword").permitAll()
				.requestMatchers("/").permitAll()
				.requestMatchers("/signup").permitAll()
				.requestMatchers("/landing_page").permitAll()
				.requestMatchers("/images/**").permitAll()
				.requestMatchers("/do_register").permitAll()
				.requestMatchers("/maneger_meeting").hasRole("USER")
				.requestMatchers("/employee_meeting").hasRole("USER")				
				.requestMatchers("/admin_meeting").hasRole("USER").anyRequest().authenticated())
		
				.exceptionHandling(configurer->configurer
						.accessDeniedPage("/access_denied"))
		
				.formLogin(form -> form.loginPage("/showloginpage").loginProcessingUrl("/authenticateUser").permitAll()
						
						.successHandler((request, response, authentication) -> {
						    String username1 = authentication.getName();
						    User userEntity = userService.getUserEntityByEmail(username1);
						    request.getSession().setAttribute("userEntity", userEntity);
						    response.sendRedirect("/");
						})

						
//						.successHandler((request, response, authentication) -> {
//							// Access the authenticated user's username
//							String username1 = authentication.getName();
//							// For example, you can set it in the session
//							
//							System.out.println(userService.getUserEntityByEmail(username1).getName()+"---->");
//							User userEntity=userService.getUserEntityByEmail(username1);
//							String username=userEntity.getName();
//							System.out.println(userEntity+"---->");
//							
//							request.getSession().setAttribute("userEntity",userEntity );
//							User userEntityFromSession = (User) request.getSession().getAttribute("userEntity");
//							System.out.println("User from session: " + userEntityFromSession);
//
//							response.sendRedirect("/");
//
//						})
						
						)
				.logout(logout -> logout.permitAll()
						
						);
				

		return http.build();
	}
}
