package com.stackroute.activitystream.dao;

import java.util.List;

public interface UserDAO 
{
	public boolean addUser(User user);
	public boolean deleteUser(User user);
	public boolean updateUser(User user);
	public User getUser(String loginID);
	public boolean validateUser(User user);
	public List<User> getAllUser();
}
