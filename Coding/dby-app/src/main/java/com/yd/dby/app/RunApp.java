package com.yd.dby.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 说明: 佛祖镇楼 
                 _ooOoo_
                o8888888o
                88" . "88
                (| -_- |)
                 0\ = /0
                                   ＿＿/'---'\＿＿
              . '\\| |//' .
             / \\|||:|||// \
            / _||||-:-||||_ \
             | |\\\ - ///| |
           | \_|''\---/''|_/ |
            \.-\__ '-' __/-./
          __'. .'/--.--\'. .'__
        .""'<'.__\_<|>_/__.'>'"".
        ||:'-\'.:'\ _ /':.'/-':||
         \\'-.\_ __\ /__ _/.-'//
   ====='-.__'-.__\___/__.-'__.-'=====
                 '=---='
  .....................................
                                 佛祖镇楼         BabyBoy
                     佛曰:
                         写字楼里写字间,写字间里程序员;
                         程序人员写程序,又拿程序换酒钱.
                         酒醒只在网上坐,酒醉还来网上眠;
                         酒醉酒醒日复日,网上网下年复年.
                         但愿老死电脑间,不愿鞠躬老板前;
                         奔驰宝马贵者趣,公交自行程序员.
                         别人笑我忒疯癫,我笑自己命太贱;
                         不见满街漂亮妹,哪个归得程序员?
 */
@SpringBootApplication
@MapperScan({ "com.yd.dby.app.mapper" })
@EnableSwagger2
public class RunApp {
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(RunApp.class, args);
	}

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2).genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(false).pathMapping("/").select()
				.paths(PathSelectors.regex("/*/.*"))// 过滤的接口
				.build().apiInfo(new ApiInfo("", "", "0.1", "", new Contact("", "", ""), "", ""));
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
