package com.yenmin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = { "com.yenmin.*" })
@EntityScan(basePackages = "com.yenmin")
@EnableMongoRepositories(basePackages = "com.yenmin")
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Docket postsApi() {

		return new Docket(DocumentationType.SWAGGER_2).globalRequestParameters(null).select()
				.apis(RequestHandlerSelectors.basePackage("com.yenmin.controller")).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		// Update the swagger version if API version changes
		return new ApiInfoBuilder().title("Yenmin template API").version("1.0.1")
				.description("Yenmin Service Template for developers").build();
	}

}
