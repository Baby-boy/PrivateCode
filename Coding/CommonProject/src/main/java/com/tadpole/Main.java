package com.tadpole;

import java.util.Properties;

import javax.servlet.MultipartConfigElement;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.async.DeferredResult;

import com.github.pagehelper.PageHelper;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan({ "com.tadpole.mapper" })
@EnableSwagger2 // Restful插件
public class Main {
	private static final String TYPE_ALIASES_PACKAGE = "com.tadpole.entity";
	private static final String MAPPER_LOCATION = "classpath:/mybatis/**/TadpoleMapper*.xml";

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public Docket swaggerApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).genericModelSubstitutes(DeferredResult.class)
				.useDefaultResponseMessages(false).forCodeGeneration(false).pathMapping("/").select()
				.paths(PathSelectors.regex("/*/.*")).build()
				.apiInfo(new ApiInfo("", "", "0.1", "", new Contact("", "", ""), "", ""));
		return docket;
	}

	@Bean
	@Autowired
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		// 分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
		// 添加插件
		sqlSessionFactoryBean.setPlugins(new Interceptor[] { pageHelper });
		sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);

		sqlSessionFactoryBean
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 设置文件上传大小限制
		factory.setMaxFileSize("1000MB");
		factory.setMaxRequestSize("1000MB");
		// 设置文件上传中location地址
		factory.setLocation("/");
		return factory.createMultipartConfig();
	}

}