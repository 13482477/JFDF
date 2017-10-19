package com.jhonelee.security.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.jhonelee.security")
@EntityScan(basePackages = "com.jhonelee.security")
@ComponentScan(basePackages = "com.jhonelee.security")
public class SecurityConfig {


}
