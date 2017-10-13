package com.jhonelee.jfdf.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author lizhiqiang
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.jhonelee"))
					.paths(PathSelectors.any())
					.build();
	}
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("用户中心")
				.description("用户中心接口说明文档")
				.contact(new Contact("李志强", null, "13482477@qq.com"))
				.license("Siebre.com").licenseUrl("#")
				.version("1.0-SNAPSHOT")
				.build();
	}

}
