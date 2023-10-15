package com.qsp.springstudent.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig 
{
	@Bean
	public Docket getDocket()
	{
		Contact contact= new Contact("Qspiders","https://qspiders.com", "qspiders@gmail.com");
		List<VendorExtension> list=new ArrayList<VendorExtension>();
		ApiInfo apiInfo=new ApiInfo("Student Management", "used to manage details of student", "version 1.0", "1 year of free service", contact, "www.sms.com", "www.sms.com", list);
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.qsp.springstudent")).build().apiInfo(apiInfo).useDefaultResponseMessages(false);
	}
}
//url of swagger--> http://localhost:8080/swagger-ui.html#
