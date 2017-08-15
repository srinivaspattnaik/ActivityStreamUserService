package com.stackroute.activitystream.test;

import static org.junit.Assert.assertTrue;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.stackroute.activitystream.UserServiceBoot;
import com.stackroute.activitystream.userutility.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserServiceBoot.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT,classes=UserServiceBoot.class)
public class UserServiceControllerTest 
{
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
		
	@Before
	public void setup() throws Exception 
	{
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testCaseForCreateUser()throws Exception
	{
	    //this.mockMvc.perform(post("http://localhost:8087/createUser")).andExpect(status().isAccepted());
	    //assertEquals("application/json;charset=UTF-8",mvcResult.getResponse().getContentType());
		
		User user=new User();
		user.setEmail_id("sunitakoul@niit.com");
		user.setPassword("12345678");
		user.setUsername("Sunita Koul");
		user.setMobile("9988112233");
		
		
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		System.out.println(entity);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8087/createUser",HttpMethod.POST, entity, String.class);
		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/createUser"));
	}
	
	
	/*@Test
	public void testCaseForAllUserDisplayDataWise()throws Exception
	{
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		String expected="{statusCode:null,statusDesc:null,email_id:nikita@gmail.com,password:1234569999,username:Nikita Kumari Pathak,mobile:9998811122}";
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8087/getAllUsers/",HttpMethod.GET, entity, String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}*/	
	
	@Test
	public void testCaseForUserDisplayDataWise()throws Exception
	{
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		String expected="{\"statusCode\":null,\"statusDesc\":null,\"email_id\":\"nikita@gmail.com\",\"password\":\"1234569999\",\"username\":\"Nikita Kumari Pathak\",\"mobile\":\"9998811122\"}";
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8087/getUser/nikita@gmail.com",HttpMethod.GET, entity, String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	public void testCaseForAllUserDisplay()throws Exception
	{
	    this.mockMvc.perform(get("http://localhost:8087/getAllUsers")).andExpect(status().isOk());
	}
}
