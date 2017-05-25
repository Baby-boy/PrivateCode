package com.yd.dby.a.sys.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yd.dby.utils.thymeleaf.dialect.YdDialectAjax;
import com.yd.dby.utils.thymeleaf.dialect.YdDialectHelper;

@Configuration
public class YdSysConfThymeleaf {
	@Bean
	public WebMvcConfigurerAdapter forwardToIndex() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				if (!registry.hasMappingForPattern("/assets/**")) {
					registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
				}
			}
		};
	}

	@Bean
	public YdDialectAjax ydDialectAjax() {
		return new YdDialectAjax();
	}

	@Bean
	public YdDialectHelper ydHelperDialect() {
		return new YdDialectHelper();
	}

}
