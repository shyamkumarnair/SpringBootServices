package com.handson.user.search;

import java.util.List;

import com.handson.user.json.User;

public interface UserSearch {

	List<User> search(String... searchParms);
}
