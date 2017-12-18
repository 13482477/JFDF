package com.jhonelee.jfdf.conf;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
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
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.jhonelee.jfdf.security.authentication.FeedbackLoginInfoAuthenticationFailureHandler;
import com.jhonelee.jfdf.security.metadatasource.DatabaseMetadataSource;
import com.jhonelee.jfdf.security.metadatasource.DelegateMetadataSource;
import com.jhonelee.jfdf.security.userdetail.service.CachedUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.addFilterBefore(this.filterSecurityInterceptor(), FilterSecurityInterceptor.class)
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/index/page")
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
	public FilterSecurityInterceptor filterSecurityInterceptor() {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setAccessDecisionManager(this.accessDecisionManager());
		filterSecurityInterceptor.setSecurityMetadataSource(this.securityMetadataSource());
		filterSecurityInterceptor.setRejectPublicInvocations(true);
		return filterSecurityInterceptor;
	}
	
	
	@Bean
	public DatabaseMetadataSource databaseMetadataSource() {
		DatabaseMetadataSource databaseMetadataSource = new DatabaseMetadataSource();
		return databaseMetadataSource;
	}
	
	@Bean
	public DefaultFilterInvocationSecurityMetadataSource configMetadataSource() {
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		
		requestMap.put(new AntPathRequestMatcher("/"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/login"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/logout"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.css"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.js"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.woff"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.woff2"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.css.map"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.ttf"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.png"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.jpg"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.jpeg"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.gif"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/**/*.ico"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/v2/api-docs"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/swagger*/**"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/file/*"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		requestMap.put(new AntPathRequestMatcher("/menu/refresh"),  org.springframework.security.access.SecurityConfig.createList("permitAll"));
		
		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		
		ExpressionBasedFilterInvocationSecurityMetadataSource configMetadataSource = new ExpressionBasedFilterInvocationSecurityMetadataSource(requestMap, webSecurityExpressionHandler);
		return configMetadataSource;
	}
	
	@Bean
	public DelegateMetadataSource securityMetadataSource() {
		DelegateMetadataSource securityMetadataSource = new DelegateMetadataSource();
		return securityMetadataSource;
	}
	
	@Bean
	public AccessDecisionManager accessDecisionManager() {
		 List<AccessDecisionVoter<? extends Object>> decisionVoters = Arrays.asList(
	        new WebExpressionVoter(),
	        this.roleVoter(),
	        this.authenticatedVoter());
	    return new AffirmativeBased(decisionVoters);
	}
	
	@Bean
	public AuthenticatedVoter authenticatedVoter() {
		return new AuthenticatedVoter();
	}
	
	@Bean
	public RoleVoter roleVoter() {
		RoleVoter roleVoter = new RoleVoter();
		roleVoter.setRolePrefix("");
		return roleVoter;
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.debug(false);
	}
	
	


}
