package com.jhonelee.jfdf.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.jhonelee.jfdf.security.authentication.FeedbackLoginInfoAuthenticationFailureHandler;
import com.jhonelee.jfdf.security.metadatasource.DatabaseMetadataSource;
import com.jhonelee.jfdf.security.rolevoter.FullyMatchRoleVoter;
import com.jhonelee.jfdf.security.securityInterceptor.HttpDynamiceSecurityInterceptor;
import com.jhonelee.jfdf.security.userdetail.service.CachedUserDetailsService;

@Configuration
@EnableWebSecurity
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
					"/**/*.ico",
					"/v2/api-docs",
					"/swagger*/**",
					}).permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/index")
			.failureHandler(new FeedbackLoginInfoAuthenticationFailureHandler("/login"))
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
			.and()
		.csrf();
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
//		web.debug(true);
	}
	
	


}
