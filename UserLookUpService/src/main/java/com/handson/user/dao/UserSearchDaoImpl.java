/**
 * 
 */
package com.handson.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handson.user.json.User;
import com.handson.user.search.UserSearchAll;
import com.handson.user.search.UserSearchByArea;
import com.handson.user.search.UserSearchByCity;
import com.handson.user.search.UserSearchById;

/**
 * @author PattathilS
 *
 */
@Service
public class UserSearchDaoImpl implements UserSearchDao {

	@Autowired
	UserSearchAll userSearchAll;
	@Autowired
	UserSearchByArea userSearchByArea;
	@Autowired
	UserSearchByCity userSearchByCity;
	@Autowired
	UserSearchById userSearchById;

	@Override
	public List<User> search() {
		return userSearchAll.search();
	}

	@Override
	public List<User> searchByCity(String city) {
		return userSearchByCity.search(city);
	}

	@Override
	public List<User> searchByCityArea(String city, String distance) {
		return userSearchByArea.search(city, distance);
	}

	@Override
	public User searchById(String id) {
		return userSearchById.search(id);
	}
}
