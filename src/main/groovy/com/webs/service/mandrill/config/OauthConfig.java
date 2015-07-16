package com.webs.service.mandrill.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OauthConfig {
	private static final Logger log = LoggerFactory.getLogger(OauthConfig.class);

	@Value("${API_BASE_URL}/oauth/access_token")
	private String tokenUrl;

	@Value("${OAUTH_CLIENT_ID}")
	private String clientId;

	@Value("${OAUTH_CLIENT_SECRET}")
	private String clientSecret;

	@Bean(name = "oauth2RestTemplate")
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
		log.debug("Creating Oauth2 Rest Template");
		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
	}

	@Bean
	OAuth2ProtectedResourceDetails resource() {
		log.debug("Creating resource with token URL: '{}', client ID: '{}', client Secret: '{}'",
				tokenUrl, clientId, clientSecret);
		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
		resource.setAccessTokenUri(tokenUrl);
		resource.setClientId(clientId);
		resource.setClientSecret(clientSecret);
		return resource;
	}

}
