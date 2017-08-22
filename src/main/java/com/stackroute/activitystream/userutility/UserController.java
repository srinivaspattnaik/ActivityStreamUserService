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


@RestController
public class UserController 
{
	@Autowired
	UserDAO userDAO;
	
	@PostMapping(value="/createUser")
	public ResponseEntity<User> userRegister(@RequestBody User user)
	{
		if(userDAO.addUser(user))
		{
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<User>(user,HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	
	@PostMapping(value="/checkLogin")
	public ResponseEntity<String> checkLoginCredential(@RequestBody User user)
	{
		/*if(userDAO.validateUser(user))
		{
			return new ResponseEntity<String>("Successful Login",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("LoginID and Password Doesn't Match",HttpStatus.NOT_ACCEPTABLE);
		}*/
		
		return new ResponseEntity<String>("Login",HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user)
	{
		
		if(userDAO.updateUser(user))
		{
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<User>(user,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping(value="/deleteUser")
	public ResponseEntity<User> deleteUser(@RequestBody User user)
	{
		if(userDAO.deleteUser(user))
		{
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<User>(user,HttpStatus.NOT_ACCEPTABLE);
		}
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
