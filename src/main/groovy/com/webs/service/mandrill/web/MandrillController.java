package com.webs.service.mandrill.web;

import com.webs.service.mandrill.model.Message;
import com.webs.service.mandrill.model.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.inject.Provider;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class MandrillController {
	private static final Logger log = LoggerFactory.getLogger(MandrillController.class);

	@Value("${API_BASE_URL}")
	private String apiUrl;

	@Autowired
	private OAuth2RestOperations oauth2RestTemplate;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	Provider<Message> messageProvider;

	@RequestMapping(method = RequestMethod.GET)
	public String getRoot() {
		log.debug("Processing request to root '/'");
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String postRoot(@RequestParam String userId, Model model) {
		log.debug("Processing POST request to email Id '{}'", userId);

		String request = apiUrl + "/sites/" + userId;
		log.debug("Doing User ID request for site: {}", request);
		UserInfo result =
				oauth2RestTemplate.getForObject(request, UserInfo.class);
		log.debug("Received result:{}", result);

		model.addAttribute("email", result.getOwnerFullName());
		model.addAttribute("title", result.getTitle());
		model.addAttribute("username", result.getUsername());

		return "email";
	}

	@RequestMapping(path = "/send", method = RequestMethod.POST)
	public String doSend(@RequestParam String email,
	                     @RequestParam String title,
	                     @RequestParam String username,
	                     @RequestParam String emailbody) throws IOException {
		log.debug("Processing POST request to email'{}', title: '{}', username: '{}'",
				email, title, username);
		log.debug("Message body: '{}'", emailbody);

		Message postBody = messageProvider.get();
		postBody.setMessageDetails(emailbody, email, title);
		log.debug("Posting message: {}: with body: {}", postBody.toJson());

		ResponseEntity<List> response = restTemplate.postForEntity("https://mandrillapp.com/api/1.0/messages/send.json",
				postBody.toJson(),
				List.class
		);

		log.info("Returned response status: {}, body: {}", response.getStatusCode(), response.getBody());

		return "email";
	}
}