package com.cf.dao;

import java.util.List;

import com.cf.entity.User;

public interface UserDao {

	public abstract User getUser(String userId);
	public abstract boolean saveUser(User user);
	public List<User> getAllUsers();
}
