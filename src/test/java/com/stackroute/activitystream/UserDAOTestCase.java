package com.stackroute.activitystream;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activitystream.dao.User;
import com.stackroute.activitystream.dao.UserDAO;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes =UserServiceBoot.class)
public class UserDAOTestCase 
{
	@Autowired
	UserDAO userDAO;
	
	@Ignore
	@Test
	public void userInsertionTest()
	{
		User user=new User();
		user.setEmail_id("niket@gmail.com");
		user.setPassword("pass@1234");
		user.setUsername("Niket Kumar");
		user.setMobile("9998881212");
		assertTrue("Problem in Insertion",userDAO.addUser(user));
	}
	
	@Ignore
	@Test
	public void userUpdationTest()
	{
		User user=new User();
		user.setEmail_id("niket@gmail.com");
		user.setPassword("pass12345");
		user.setUsername("Niket Kumar");
		user.setMobile("9998881212");
		
		assertTrue("Problem in Updation",userDAO.updateUser(user));
	}
	
	@Ignore
	@Test
	public void userDeletionTest()
	{
		User user=new User();
		user.setEmail_id("niket@gmail.com");
		user.setPassword("pass12345");
		assertTrue("Problem in Deletion",userDAO.deleteUser(user));
	}
	
	@Test
	public void testCaseForgetUser()
	{
		User user=userDAO.getUser("niket@gmail.com");
		assertNotNull("User Does not Exist",user);
	}
	
	@Test
	public void testCaseForgetAllUsers()
	{
		List<User> listusers=userDAO.getAllUser();
		assertNotNull("Problem Occur in retrieving all Users",listusers);
	}
}
