package com.bancointer.bancointer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BancointerApplication {

	public static void main(String[] args) {

		SpringApplication.run(BancointerApplication.class, args);
	}

	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.bancointer.bancointer.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());

	}

	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API Banco Inter")
				.description("Api ")
				.version("1.0.0")
				.contact(new Contact("", "", "thomazrdamasceno@gmail.com"))
				.build();
	}

}
