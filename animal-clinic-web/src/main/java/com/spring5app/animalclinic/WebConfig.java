package com.spring5app.animalclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring5app.animalclinic.converters.DateToStringConverter;
import com.spring5app.animalclinic.converters.StringToDateConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addFormatters(FormatterRegistry registry) {		
		registry.addConverter(new StringToDateConverter());
		registry.addConverter(new DateToStringConverter());
	}
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("logout");
	}

}
