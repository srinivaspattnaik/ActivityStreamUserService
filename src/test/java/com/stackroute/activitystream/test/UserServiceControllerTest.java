package com.stackroute.activitystream.test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.activitystream.UserServiceBoot;
import com.stackroute.activitystream.controller.UserController;
import com.stackroute.activitystream.dao.User;
import com.stackroute.activitystream.dao.UserDAO;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserServiceBoot.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT,classes=UserServiceBoot.class)
public class UserServiceControllerTest 
{
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Mock
	private UserDAO userDAO;
	
	@InjectMocks
	private UserController userController;
	
		
	@Before
	public void setup() throws Exception 
	{
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Ignore
	@Test
	public void testCaseForCreateUser()throws Exception
	{
		User user=new User();
		user.setEmail_id("dimple@gmail.com");
		user.setPassword("pass@1234");
		user.setUsername("Dimple Baid");
		user.setMobile("9998881213");
		
		mockMvc.perform(post("/createUser").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("http://localhost/createUser")));
		
	}
	@Ignore
	@Test
	public void testCaseForUpdateUser()throws Exception
	{
		User user=new User();
		user.setEmail_id("dimple@gmail.com");
		user.setPassword("pass12345");
		user.setUsername("Dimple Baid");
		user.setMobile("9998881213");
		
		mockMvc.perform(post("/updateUser").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
        .andExpect(status().isOk());
	}
	
	@Ignore
	@Test
	public void testCaseForDeleteUser()throws Exception
	{
		User user=new User();
		user.setEmail_id("dimple@gmail.com");
		user.setPassword("pass12345");
		user.setUsername("Dimple Baid");
		user.setMobile("9998881213");
		
		mockMvc.perform(post("/deleteUser").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
        .andExpect(status().isOk());
	}
	
	
	@Test
	public void testCaseForGetUser()throws Exception
	{
		 User user=new User();
		 user.setEmail_id("vinu@gmail.com");
		 user.setMobile("8991112321");
		 user.setUsername("Vinu Kumar");
		 user.setPassword("you@12345");
		 
		 when(userDAO.getUser("vinu@gmail.com")).thenReturn(user);
		 
		 this.mockMvc.perform(get("/getUser/{emailid}","vinu@gmail.com")).andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$.email_id", is("vinu@gmail.com")))
         .andExpect(jsonPath("$.mobile", is("8991112321")));
		
	}
	@Ignore
	@Test
	public void testCaseForGetAllUsers()throws Exception
	{	
	    this.mockMvc.perform(get("http://localhost:8087/getAllUsers")).andExpect(status().isOk())
	    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	    .andExpect(jsonPath("$", hasSize(8)))
	    .andExpect(jsonPath("$[0].email_id", is("nikita@gmail.com")))
        .andExpect(jsonPath("$[0].mobile", is("9998811122")));
	}
	
	 public static String asJsonString(final Object obj) 
	 {
	        try 
	        {
	            return new ObjectMapper().writeValueAsString(obj);
	        } 
	        catch (Exception e) 
	        {
	            throw new RuntimeException(e);
	        }
	    }
}
