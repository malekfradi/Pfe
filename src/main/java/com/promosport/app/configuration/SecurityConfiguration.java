package com.promosport.app.configuration;

import com.promosport.app.service.UtilisateurService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.promosport.app.model.RoleEnum.ADMIN;
import static com.promosport.app.model.RoleEnum.USER;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider(UtilisateurService userService) {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer ->
				configurer
					.requestMatchers("css/**", "js/**", "img/**", "login", "register", "/access-denied").permitAll()
					.requestMatchers(HttpMethod.GET, "/dashboard").hasAnyAuthority(ADMIN.name())
					.requestMatchers(HttpMethod.GET, "/home").hasAnyAuthority(USER.name())
					.requestMatchers(HttpMethod.GET, "/results").hasAnyAuthority(USER.name())
					.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
			)
			.formLogin(form ->
				form
					.loginPage("/login")
					.loginProcessingUrl("/perform_login")
					.defaultSuccessUrl("/dashboard")
					.successHandler((request, response, authentication) -> {
						if (authentication.getAuthorities().contains(ADMIN))
							response.sendRedirect("/dashboard");
						else
							response.sendRedirect("/home");
					})
					.permitAll()
			)
//				.logout(LogoutConfigurer::permitAll)
			.exceptionHandling(configurer ->
				configurer
					.accessDeniedPage("/access-denied"))


//        http.httpBasic(Customizer.withDefaults());
//        http.csrf(AbstractHttpConfigurer::disable);
			.csrf().disable();
		return http.build();
	}

}
