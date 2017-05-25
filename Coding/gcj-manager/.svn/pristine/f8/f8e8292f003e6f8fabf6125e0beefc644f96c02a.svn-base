package com.yd.gcj.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class YdSysConfThymeleaf {
	
	@Bean
	public WebMvcConfigurerAdapter forwardToIndex() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				if (!registry.hasMappingForPattern("/static/**")) {
					registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
				}
			}
		};
	}
}
