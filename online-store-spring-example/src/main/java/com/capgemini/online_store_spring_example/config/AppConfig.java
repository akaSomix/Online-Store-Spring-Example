package com.capgemini.online_store_spring_example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.annotation.SessionScope;
//import org.springframework.web.servlet.ViewResolver;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.capgemini.online_store_spring_example.aspects.LogAspect;
import com.capgemini.online_store_spring_example.cart.CartEntity;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.capgemini.online_store_spring_example")
public class AppConfig {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean(name = "cart")
	@SessionScope
	public CartEntity sessionCart() {
		return new CartEntity();
	}

	/*
	 * Thymeleaf Configurations
	 */
//	@Bean
//	public SpringResourceTemplateResolver templateResolver() {
//		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//		resolver.setApplicationContext(applicationContext);
//		resolver.setPrefix("classpath:/templates/");
//		resolver.setSuffix(".html");
//		
//		return resolver;
//	}
//
//	@Bean
//	public SpringTemplateEngine templateEngine() {
//		SpringTemplateEngine engine = new SpringTemplateEngine();
//		engine.setTemplateResolver(templateResolver());
//		engine.setEnableSpringELCompiler(true);
//		
//		return engine;
//	}
//	
//	@Bean
//	public ViewResolver thymeleafViewResolver() {
//		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//		viewResolver.setTemplateEngine(templateEngine());
//		viewResolver.setOrder(0);
//		
//		return viewResolver;
//	}
	
	/*
	 * Logging Aspect Configuration
	 */
	@Bean
	public LogAspect logAspect() {
		return new LogAspect();
	}
	
	
}
