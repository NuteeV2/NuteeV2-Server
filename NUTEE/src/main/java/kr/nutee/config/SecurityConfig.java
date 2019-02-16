package kr.nutee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.nutee.service.MyAuthenticationProvider;

/**
 * Security configuration class
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	MyAuthenticationProvider myAuthenticationProvider;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/res/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/studentrepresentative/**").hasAuthority("ROLE_STUDENTREPRESENTATIVE")
				.antMatchers("/club/**").hasAuthority("ROLE_CLUB").antMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_STUDENTREPRESENTATIVE", "ROLE_CLUB")
				.antMatchers("/warning/**").hasAuthority("ROLE_WARNING").antMatchers("/suspension/**")
				.hasAuthority("ROLE_SUSPENSION").antMatchers("/comment/**").permitAll().antMatchers("/guest/**").permitAll().antMatchers("/").permitAll()
				.antMatchers("/**").authenticated();
		http.csrf().disable();
		http.formLogin().loginProcessingUrl("/guest/login")
				.failureUrl("/guest/error").defaultSuccessUrl("/guest/success", true).usernameParameter("nickname")
				.passwordParameter("pw");
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/guest/index").invalidateHttpSession(true);
		http.authenticationProvider(myAuthenticationProvider);
	}
}
