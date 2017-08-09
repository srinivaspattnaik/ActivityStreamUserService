package com.stackroute.activitystream.userutility;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userRegistrationDAO")
public class UserRegistrationDAOImpl implements UserRegistrationDAO 
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean addUser(UserRegistration userRegistration)
	{
		try
		{
		sessionFactory.getCurrentSession().save(userRegistration);
		return true;
		}
		catch(Exception e)
		{
		return false;	
		}
	}
	
	@Transactional
	public boolean validateUser(UserRegistration userRegistration)
	{
		System.out.println("---DAO Impl--"+userRegistration.getEmailID()+"Password:"+userRegistration.getPassword());
		try
		{
		@SuppressWarnings({ "rawtypes", "deprecation" })
		Query query=sessionFactory.getCurrentSession().createQuery("from UserRegistration where emailID=:email and password=:passwd");
		query.setParameter("email",userRegistration.getEmailID());
		query.setParameter("passwd",userRegistration.getPassword());
		UserRegistration userRegistration1=(UserRegistration)query.list().get(0);
		if(userRegistration1!=null)
			return true;
		else
			return false;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	
	public UserRegistration getUser(String emailID)
	{
		Session session=sessionFactory.openSession();
		UserRegistration userRegistration=session.get(UserRegistration.class,emailID);
		session.close();
		return userRegistration;
	}
	
	@Transactional
	public boolean deleteUser(UserRegistration userRegistration)
	{
		try
		{
		sessionFactory.getCurrentSession().delete(userRegistration);
		return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}
	
	@Transactional
	public boolean updateUser(UserRegistration userRegistration)
	{
		try
		{
		sessionFactory.getCurrentSession().update(userRegistration);
		return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}
	
	@Transactional
	public List<UserRegistration> getAllUser() 
	{
		List<UserRegistration> listUsers=(List<UserRegistration>)sessionFactory.getCurrentSession().createQuery("from UserRegistration").list();
		return listUsers;
	}

}
