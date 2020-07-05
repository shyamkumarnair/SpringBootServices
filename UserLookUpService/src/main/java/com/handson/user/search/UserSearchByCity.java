/**
 * 
 */
package com.handson.user.search;

import java.util.Arrays;
import java.util.List;

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
public class UserSearchByCity {

	@Value("${source.users.bycity.url}")
	private String userByCityUrl;

	@Autowired
	private RestTemplate userRestServiceTemplate;

	public List<User> search(String city) {
		return Arrays.asList(userRestServiceTemplate.getForObject(userByCityUrl, User[].class, city));
	}
}
