package com.jhonelee.security.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.jhonelee.security.metadatasource.DatabaseMetadataSource;
import com.jhonelee.security.rolevoter.FullyMatchRoleVoter;
import com.jhonelee.security.securityInterceptor.HttpDynamiceSecurityInterceptor;
import com.jhonelee.security.userdetail.service.CachedUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "com.jhonelee.security")
@EntityScan(basePackages = "com.jhonelee.security")
@ComponentScan(basePackages = "com.jhonelee.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers(new String[] {
					"/login",
					"/logout",
					"/**/*.css",
					"/**/*.js",
					"/**/*.woff",
					"/**/*.woff2",
					"/**/*.css.map",
					"/**/*.ttf",
					"/**/*.png",
					"/**/*.jpg",
					"/**/*.jpeg",
					"/**/*.gif",
					}).permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/index")
			.and()
		.logout()
			.logoutUrl("/login")
			.logoutSuccessUrl("/logout");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.authenticationProvider());
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		daoAuthenticationProvider.setSaltSource(this.saltSource());
		return daoAuthenticationProvider;
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		CachedUserDetailsService cachedUserDetailsService = new CachedUserDetailsService();
		return cachedUserDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ReflectionSaltSource saltSource() {
		ReflectionSaltSource saltSource = new ReflectionSaltSource();
		saltSource.setUserPropertyToUse("username");
		return saltSource;
	}
	
	@Bean
	public FilterSecurityInterceptor filterSecurityInterceptor() {
		FilterSecurityInterceptor filterSecurityInterceptor = new HttpDynamiceSecurityInterceptor();
		filterSecurityInterceptor.setSecurityMetadataSource(this.securityMetadataSource());
		filterSecurityInterceptor.setAccessDecisionManager(this.accessDecisionManager());
		return filterSecurityInterceptor;
	}
	
	@Bean
	public DatabaseMetadataSource securityMetadataSource() {
		DatabaseMetadataSource databaseMetadataSource = new DatabaseMetadataSource();
		return databaseMetadataSource;
	}
	
	@Bean
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> voters = new ArrayList<AccessDecisionVoter<? extends Object>>();
		voters.add(this.authenticatedVoter());
		voters.add(this.fullyMatchRoleVoter());
		
		AffirmativeBased accessDecisionManager = new AffirmativeBased(voters);
		return accessDecisionManager;
	}
	
	@Bean
	public AuthenticatedVoter authenticatedVoter() {
		return new AuthenticatedVoter();
	}
	
	@Bean
	public FullyMatchRoleVoter fullyMatchRoleVoter() {
		return new FullyMatchRoleVoter();
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.debug(true);
	}
	
	


}
