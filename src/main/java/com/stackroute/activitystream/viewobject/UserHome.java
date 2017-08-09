package com.stackroute.activitystream.viewobject;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.activitystream.userutility.Message;
import com.stackroute.activitystream.userutility.UserRegistration;

public class UserHome 
{
	private String emailid;
	private String username;
	
	private List<String> allCircles=new ArrayList<String>();
	private List<String> myCircles=new ArrayList<String>();
	private List<UserRegistration> allUsers=new ArrayList<UserRegistration>();
	private List<Message> circleMessages=new ArrayList<Message>();
	private List<Message> personalMessage=new ArrayList<Message>();
	
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getAllCircles() {
		return allCircles;
	}
	public void setAllCircles(List<String> allCircles) {
		this.allCircles = allCircles;
	}
	public List<String> getMyCircles() {
		return myCircles;
	}
	public void setMyCircles(List<String> myCircles) {
		this.myCircles = myCircles;
	}
	public List<UserRegistration> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<UserRegistration> allUsers) {
		this.allUsers = allUsers;
	}
	public List<Message> getCircleMessages() {
		return circleMessages;
	}
	public void setCircleMessages(List<Message> circleMessages) {
		this.circleMessages = circleMessages;
	}
	public List<Message> getPersonalMessage() {
		return personalMessage;
	}
	public void setPersonalMessage(List<Message> personalMessage) {
		this.personalMessage = personalMessage;
	}
	
	
}
