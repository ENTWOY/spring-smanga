package com.smanga.proyecto;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

		http
        .authorizeHttpRequests()
	        .requestMatchers("/index", "/contacto", "/nosotros", "/login", "/logout", "/manga/detalle/**", "/registro").permitAll()
	        .requestMatchers("").hasRole("NEKO")
	        .requestMatchers("/resources/**").permitAll()
	        .requestMatchers("/**").hasRole("ADMIN")
	        .anyRequest().authenticated()
	        .and()
        .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/index")
            .and()
        .logout()
            .logoutUrl("/logout")
            .permitAll();
		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin")
					.password(passwordEncoder().encode("123"))
					.roles("ADMIN")
					.build());
		manager.createUser(User.withUsername("neko")
				.password(passwordEncoder().encode("123"))
				.roles("NEKO")
				.build());
		return manager;
	}
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService())
									.passwordEncoder(passwordEncoder())
									.and()
									.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
