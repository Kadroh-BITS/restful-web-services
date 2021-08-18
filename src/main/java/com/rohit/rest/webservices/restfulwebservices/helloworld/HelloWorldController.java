package com.rohit.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	
	//MessageSource is feature provided by Spring FW to pick up the properties from message bundles
	//that is message.properties file in our case.	
	@Autowired
	private MessageSource messageSource; 
	
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	
	//hello-world-bean
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World %s", name));
	}
	
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized(
			//@RequestHeader(name="Accept-Language", required=false) Locale locale
			) {
		//Basically we are accepting locales as one of the request headers
		 
		return messageSource.getMessage("good.morning.message", null, "Good Morning"
				, LocaleContextHolder.getLocale());
		//Good Morning is Default message if we are not able to pick it up or if locale is not provided
	}

}
