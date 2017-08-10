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
public class UserRegistrationController 
{
	@Autowired
	RestTemplate restTemplate;
	
	String MSGSERVICE_RESTURL="http://localhost:8089/";
	String CIRCLESERVICE_RESTURL="http://localhost:8090/";
	
	@Autowired
	UserRegistrationDAO userRegistrationDAO;
	@PostMapping(value="/createUser")
	public ResponseEntity<UserRegistration> userRegister(@RequestBody UserRegistration userRegistration)
	{
		if(userRegistrationDAO.addUser(userRegistration))
		{
			userRegistration.statusCode="1031";
			userRegistration.statusDesc="Successfully Registered";
		}
		else
		{
			userRegistration.statusCode="1032";
			userRegistration.statusDesc="Error happened while Registering.";
		}
		
		return new ResponseEntity<UserRegistration>(userRegistration,HttpStatus.ACCEPTED);
	}
	
	//What is this method do? What is the meaning of "simpleRestServiceConsuming" ??
	public UserHome simpleRestServiceConsuming(String circleid)
	{
		System.out.println("Service Consuming Successful");
		//Why this mail id hard coded?
		String emailid="vinod@gmail.com";
		
		//Whye used new key word??
		UserHome userHome=new UserHome();
		
		String circle_wise_message_Url=MSGSERVICE_RESTURL+"getMessagesByCircleId/"+circleid;
		List<Message> listCircleMessages=(List<Message>)restTemplate.getForObject(circle_wise_message_Url,List.class);
		
		String all_circleid_url=CIRCLESERVICE_RESTURL+"getAllCircles";
		List<String> listAllCircleId=restTemplate.getForObject(all_circleid_url,List.class);
		
		String mycircle_url=CIRCLESERVICE_RESTURL+"getAllSubscribeCircle/"+emailid;
		List<String> myCircles=restTemplate.getForObject(mycircle_url,List.class);
		
		String myinbox_wise_message_url=MSGSERVICE_RESTURL+"getMessagesByReceiverId/"+emailid;
		List<Message> listMyInboxMessages=(List<Message>)restTemplate.getForObject(myinbox_wise_message_url,List.class);
		
		List<UserRegistration> listUsers=userRegistrationDAO.getAllUser();
		
		userHome.setAllUsers(listUsers);
		userHome.setAllCircles(listAllCircleId);
		userHome.setCircleMessages(listCircleMessages);
		userHome.setPersonalMessage(listMyInboxMessages);
		userHome.setMyCircles(myCircles);
		
		System.out.println("Service Consuming Successful");
		return userHome;
	}
	
	@PostMapping(value="/checkLogin")
	public ResponseEntity<UserHome> checkLoginCredential(@RequestBody UserRegistration userRegistration)
	{
		UserHome userHome=null;
		if(userRegistrationDAO.validateUser(userRegistration))
		{
			userRegistration.statusCode="1033";
			userRegistration.statusDesc="Successfully Login";
			System.out.println("Login Successful");
			userHome=this.simpleRestServiceConsuming("veggrp05");
		}
		else
		{
			userRegistration.statusCode="1034";
			userRegistration.statusDesc="Invalid Login ID and Password";
		}
		//You are return null   // UserHome userHome=null;
		return new ResponseEntity<UserHome>(userHome,HttpStatus.OK);
	}
	
	@PostMapping(value="/updateUser")
	public ResponseEntity<UserRegistration> updateUser(@RequestBody UserRegistration userRegistration)
	{
		
		if(userRegistrationDAO.updateUser(userRegistration))
		{
			userRegistration.statusCode="1035";
			userRegistration.statusDesc="Successfully Updated";
		}
		else
		{
			userRegistration.statusCode="1036";
			userRegistration.statusDesc="Error happened While Updated";
		}
		return new ResponseEntity<UserRegistration>(userRegistration,HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/deleteUser")
	public ResponseEntity<UserRegistration> deleteUser(@RequestBody UserRegistration userRegistration)
	{
		
		if(userRegistrationDAO.deleteUser(userRegistration))
		{
			userRegistration.statusCode="1037";
			userRegistration.statusDesc="Successfully User Deleted";
		}
		else
		{
			userRegistration.statusCode="1038";
			userRegistration.statusDesc="Error Happened While Deleting";
		}
		return new ResponseEntity<UserRegistration>(userRegistration,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="/getAllUsers")
	public ResponseEntity<List<UserRegistration>> getAllUsers()
	{
		//userRegistrationDAO can rename to userDAO.
		//This DAO is not only for registration, but also for delete, validate etc.,
		
		List<UserRegistration> listUsers=userRegistrationDAO.getAllUser();
		return new ResponseEntity<List<UserRegistration>>(listUsers,HttpStatus.OK);
	}
	
	@GetMapping(value="/getTest")
	public ResponseEntity<String> getTest()
	{
		return new ResponseEntity<String>("Hello String",HttpStatus.OK);
	}
	
	@GetMapping(value="/getUser/{emailid}")
	public ResponseEntity<UserRegistration> getUser(@PathVariable("emailid")String emailid)
	{
		emailid=emailid+".com";
		UserRegistration userRegistration=userRegistrationDAO.getUser(emailid);
		return new ResponseEntity<UserRegistration>(userRegistration,HttpStatus.OK);
	}
}
