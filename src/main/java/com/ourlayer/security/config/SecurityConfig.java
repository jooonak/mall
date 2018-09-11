package com.ourlayer.security.config;

import com.ourlayer.security.handler.AuthSuccessHandler;
import com.ourlayer.security.service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MemberDetailsService memberDetailsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers(/* ignore patterns */"/css/**", "/fonts/**", "/img/**", "/js/**");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
					.and()
				.authorizeRequests()
						.antMatchers("/sign-in", "/forgot-password", "/update-password").anonymous()
						.antMatchers("/admin/**").hasRole("ADMIN")
						.antMatchers("/**").permitAll()
					.and()
				.formLogin()
						.loginPage("/sign-in")
						.loginProcessingUrl("/login")
						.successHandler(authSuccessHandler())
//						.failureHandler()
					.and()
				.logout()
						.logoutUrl("/logout")
						.clearAuthentication(true)
						.logoutSuccessUrl("/sign-in?bye")
//						.deleteCookies( /* cookie to need remove */ )
						.invalidateHttpSession(true)
					.and()
				.sessionManagement()
						.maximumSessions(2)
						.expiredUrl("/expired");
	}

	@Autowired
	public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder () { return new BCryptPasswordEncoder();	}

	@Bean  // 세션이 만료되는 Event 를 감지하는 Listener
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
	}

	@Bean
	public AuthenticationSuccessHandler authSuccessHandler() {
		return new AuthSuccessHandler();
	}

}
