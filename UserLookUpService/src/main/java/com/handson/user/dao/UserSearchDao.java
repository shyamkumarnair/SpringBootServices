package com.handson.user.dao;

import java.util.List;

import com.handson.user.json.User;

public interface UserSearchDao {

	User searchById(String id);

	List<User> search();

	List<User> searchByCity(String city);

	List<User> searchByCityArea(String city, String distance);
}
