package com.stackroute.activitystream.userutility;

import java.util.List;

public interface UserRegistrationDAO 
{
	public boolean addUser(UserRegistration userRegistration);
	public boolean deleteUser(UserRegistration userRegistration);
	public boolean updateUser(UserRegistration userRegistration);
	public UserRegistration getUser(String loginID);
	public boolean validateUser(UserRegistration userRegistration);
	public List<UserRegistration> getAllUser();
}
