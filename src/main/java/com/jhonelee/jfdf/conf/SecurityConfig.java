package com.jhonelee.jfdf.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jhonelee.jfdf.security.authentication.FeedbackLoginInfoAuthenticationFailureHandler;
import com.jhonelee.jfdf.security.metadatasource.DatabaseMetadataSource;
import com.jhonelee.jfdf.security.securityInterceptor.CustomSecurityInterceptor;
import com.jhonelee.jfdf.security.userdetail.service.CachedUserDetailsService;

@Configuration
@EnableWebSecurity
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
					"/file/*",
					}).permitAll()
//			.anyRequest().authenticated()
			.and()
		.addFilterBefore(this.customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/index")
			.failureHandler(new FeedbackLoginInfoAuthenticationFailureHandler("/login"))
			.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
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
	public CustomSecurityInterceptor customFilterSecurityInterceptor() {
		CustomSecurityInterceptor filterSecurityInterceptor = new CustomSecurityInterceptor();
		return filterSecurityInterceptor;
	}
	
	@Bean
	public DatabaseMetadataSource databaseMetadataSource() {
		DatabaseMetadataSource databaseMetadataSource = new DatabaseMetadataSource();
		return databaseMetadataSource;
	}
	
	@Bean
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> voters = new ArrayList<AccessDecisionVoter<? extends Object>>();
		voters.add(this.roleVoter());
		
		UnanimousBased accessDecisionManager = new UnanimousBased(voters);
		return accessDecisionManager;
	}
	
	@Bean
	public RoleVoter roleVoter() {
		RoleVoter roleVoter = new RoleVoter();
		roleVoter.setRolePrefix("");
		return roleVoter;
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.securityInterceptor(securityInterceptor)
		web.debug(true);
	}
	
	


}
