package com.handson.user.search;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.handson.user.json.User;

@Service
public class UserSearchAll {

	@Value("${source.users.url}")
	private String usersUrl;

	private RestTemplate userRestServiceTemplate = new RestTemplate();

	public List<User> search() {
		return Arrays.asList(userRestServiceTemplate.getForObject(usersUrl, User[].class));
	}

}
