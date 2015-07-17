package com.webs.service.mandrill.config;

import com.webs.service.mandrill.model.Message;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

@Configuration
public class MandrillConfig {

	@Value("${MANDRILL_API_KEY}")
	private String mandrillKey;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public Message message() {
		Message message = new Message();
		message.setKey(mandrillKey);
		return message;
	}

}
