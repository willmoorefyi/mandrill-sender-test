package com.webs.service.mandrill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.mustache.MustacheViewResolver;
import org.springframework.web.servlet.view.mustache.java.MustacheJTemplateFactory;

@Configuration
public class MustacheConfig {
	@Bean
	public MustacheJTemplateFactory templateFactory() {
		return new MustacheJTemplateFactory();
	}

	@Bean
	public MustacheViewResolver viewResolver(MustacheJTemplateFactory templateFactory) {
		MustacheViewResolver viewResolver = new MustacheViewResolver();
		viewResolver.setPrefix("classpath:/views/");
		viewResolver.setSuffix(".mustache");
		viewResolver.setCache(true);
		viewResolver.setTemplateFactory(templateFactory);

		return viewResolver;
	}

}

