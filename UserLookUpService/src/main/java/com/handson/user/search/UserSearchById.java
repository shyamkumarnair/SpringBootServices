/**
 * 
 */
package com.handson.user.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.handson.user.json.User;

/**
 * @author PattathilS
 *
 */
@Service
public class UserSearchById {

	@Value("${source.users.byid.url}")
	private String userByIdUrl;

	@Autowired
	private RestTemplate userRestServiceTemplate;// = new RestTemplate();

	public User search(String id) {
		return userRestServiceTemplate.getForObject(userByIdUrl, User.class, id);
	}
}
