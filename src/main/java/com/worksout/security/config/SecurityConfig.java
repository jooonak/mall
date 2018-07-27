package com.worksout.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers(/* ignore patterns */);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf()
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
					.and()
				.authorizeRequests()
						.antMatchers("/**").permitAll()
					.and()
				.formLogin()
						.loginPage("/login")
//						.successHandler()
//						.failureHandler()
					.and()
				.logout()
						.logoutUrl("/logout")
						.clearAuthentication(true)
						.logoutSuccessUrl("/login?bye")
//						.deleteCookies( /* cookie to need remove */ )
						.invalidateHttpSession(true)
					.and()
				.sessionManagement()
						.maximumSessions(2)
						.expiredUrl("/expired");
	}
}
