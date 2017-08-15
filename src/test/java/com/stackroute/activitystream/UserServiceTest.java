package com.stackroute.activitystream;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.userutility.User;
import com.stackroute.activitystream.userutility.UserDAO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes =UserServiceBoot.class)
public class UserServiceTest 
{

	@Autowired
	UserDAO userRegistrationDAO;
	
	/*	@Test
	public void userInsertionTest()
	{
		UserRegistration userRegistration=new UserRegistration();
		userRegistration.setUsername("Nikita Kumari");
		userRegistration.setPassword("1234569999");
		userRegistration.setEmailID("nikita@gmail.com");
		userRegistration.setMobile("9998811122");
		assertTrue("Problem in Insertion",userRegistrationDAO.addUser(userRegistration));
	}
	
	@Test
	public void userUpdationTest()
	{
		UserRegistration userRegistration=new UserRegistration();
		userRegistration.setUsername("Nikita Kumari Pathak");
		userRegistration.setPassword("1234569999");
		userRegistration.setEmailID("nikita@gmail.com");
		userRegistration.setMobile("9998811122");
		assertTrue("Problem in Updation",userRegistrationDAO.updateUser(userRegistration));
	}
	

	@Test
	public void userDeletionTest()
	{
		UserRegistration userRegistration=new UserRegistration();
		userRegistration.setEmailID("harsha@gmail.com");
		assertTrue("Problem in Deletion",userRegistrationDAO.deleteUser(userRegistration));
	}*/
	
}
