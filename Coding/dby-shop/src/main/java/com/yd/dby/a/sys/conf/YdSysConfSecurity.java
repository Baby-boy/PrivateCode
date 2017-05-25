package com.yd.dby.a.sys.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yd.dby.a.sys.filter.YdSysFilterSecurity;

@Configuration
public class YdSysConfSecurity {

	@Bean
	public FilterRegistrationBean AuthFilter() {
		YdSysFilterSecurity filter = new YdSysFilterSecurity();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(filter);
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
