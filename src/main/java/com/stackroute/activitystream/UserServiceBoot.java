package com.stackroute.activitystream;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.stackroute.activitystream.userutility.UserDAO;
import com.stackroute.activitystream.userutility.UserDAOImpl;


@SpringBootApplication(scanBasePackages={"com.stackroute.activitystream.userutility"})
@EntityScan(basePackages={"com.stackroute.activitystream.userutility"})
public class UserServiceBoot 
{
	public static void main(String arg[])
	{
		SpringApplication.run(UserServiceBoot.class,arg);
	}
	
	@Bean
	public SessionFactory getSessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory)
	{
		return hibernateEntityManagerFactory.getSessionFactory();
	}
	
}
