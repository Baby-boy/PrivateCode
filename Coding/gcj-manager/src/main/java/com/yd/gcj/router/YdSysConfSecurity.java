package com.yd.gcj.router;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class YdSysConfSecurity {
	
	@Bean
	public FilterRegistrationBean AuthFilter() {
		YdMangerMyFilter filter = new YdMangerMyFilter();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(filter);
		registrationBean.setOrder(1);
		return registrationBean;
	}
	

}
