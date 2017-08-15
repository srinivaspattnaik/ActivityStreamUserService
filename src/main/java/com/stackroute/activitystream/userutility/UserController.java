package com.stackroute.activitystream.userutility;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stackroute.activitystream.viewobject.UserHome;


@RestController
public class UserController 
{
	@Autowired
	RestTemplate restTemplate;
	
	String MSGSERVICE_RESTURL="http://localhost:8089/";
	String CIRCLESERVICE_RESTURL="http://localhost:8090/";
	
	@Autowired
	UserDAO userDAO;
	
	@PostMapping(value="/createUser")
	public ResponseEntity<User> userRegister(@RequestBody User user)
	{
		if(userDAO.addUser(user))
		{
			user.statusCode="1031";
			user.statusDesc="Successfully Registered";
		}
		else
		{
			user.statusCode="1032";
			user.statusDesc="Error happened while Registering.";
		}
		
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping(value="/checkLogin")
	public ResponseEntity<UserHome> checkLoginCredential(@RequestBody User user)
	{
		UserHome userHome=null;
		if(userDAO.validateUser(user))
		{
			user.statusCode="1033";
			user.statusDesc="Successfully Login";
		}
		else
		{
			user.statusCode="1034";
			user.statusDesc="Invalid Login ID and Password";
		}
		return new ResponseEntity<UserHome>(userHome,HttpStatus.OK);
	}
	
	@PostMapping(value="/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		
		if(userDAO.updateUser(user))
		{
			user.statusCode="1035";
			user.statusDesc="Successfully Updated";
		}
		else
		{
			user.statusCode="1036";
			user.statusDesc="Error happened While Updated";
		}
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/deleteUser")
	public ResponseEntity<User> deleteUser(@RequestBody User user)
	{
		
		if(userDAO.deleteUser(user))
		{
			user.statusCode="1037";
			user.statusDesc="Successfully User Deleted";
		}
		else
		{
			user.statusCode="1038";
			user.statusDesc="Error Happened While Deleting";
		}
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> listUsers=userDAO.getAllUser();
		return new ResponseEntity<List<User>>(listUsers,HttpStatus.OK);
	}
	
	@GetMapping(value="/getUser/{emailid}")
	public ResponseEntity<User> getUser(@PathVariable("emailid")String emailid)
	{
		emailid=emailid+".com";
		User user=userDAO.getUser(emailid);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
