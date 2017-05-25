package com.yd.dby;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan({ "com.yd.dby" })
@MapperScan({ "com.yd.dby.a.sys.mapper", "com.yd.dby.b.shop.mapper", "com.yd.dby.c.member.mapper", "com.yd.dby.d.seller.mapper","com.yd.dby.wx.mapper" })
public class YdMain implements ApplicationContextAware {

	private static ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(YdMain.class, args);
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) {
		context = ctx;
	}

	private static Object InvokeService(String _class, String _method, HashMap<String, Object> request) {
		try {
			return YdMain.getApplicationContext().getBean(_class).getClass().getMethod(_method, new Class[] { HashMap.class }).invoke(YdMain.getApplicationContext().getBean(_class), request);
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<String, Object>();
		}
	}

	@SuppressWarnings("unchecked")
	public static ModelAndView MAV(String _class, String _method, Map<String, HashMap<String, Object>> request) {
		ModelAndView mav;
		if (request != null && request.containsKey("conf")) {
			if (request.get("conf").containsKey("tpl")) {
				mav = new ModelAndView(request.get("conf").get("tpl").toString());
			} else {
				return new ModelAndView("/error/404");
			}
		} else {
			mav = new ModelAndView(new MappingJackson2JsonView());
		}
		mav.addAllObjects((Map<String, ?>) InvokeService(_class, _method, request.get("val")));
		return mav;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setSerializationInclusion(Include.NON_NULL);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
		return converter;
	}

}
