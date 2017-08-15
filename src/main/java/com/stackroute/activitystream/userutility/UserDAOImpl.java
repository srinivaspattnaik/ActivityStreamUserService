package com.stackroute.activitystream.userutility;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("userRegistrationDAO")
public class UserDAOImpl implements UserDAO 
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean addUser(User user)
	{
		try
		{
		sessionFactory.getCurrentSession().save(user);
		return true;
		}
		catch(Exception e)
		{
		return false;	
		}
	}
	
	@Transactional
	public boolean validateUser(User user)
	{
		try
		{
		@SuppressWarnings({ "rawtypes", "deprecation" })
		Query query=sessionFactory.getCurrentSession().createQuery("from User where emailID=:email and password=:passwd");
		query.setParameter("email",user.getEmail_id());
		query.setParameter("passwd",user.getPassword());
		User userRegistration1=(User)query.list().get(0);
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
	
	public User getUser(String emailID)
	{
		Session session=sessionFactory.openSession();
		User userRegistration=session.get(User.class,emailID);
		session.close();
		return userRegistration;
	}
	
	@Transactional
	public boolean deleteUser(User user)
	{
		try
		{
		sessionFactory.getCurrentSession().delete(user);
		return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}
	
	@Transactional
	public boolean updateUser(User user)
	{
		try
		{
		sessionFactory.getCurrentSession().update(user);
		return true;
		}
		catch(Exception e)
		{
		return false;
		}
	}
	
	@Transactional
	public List<User> getAllUser() 
	{
		List<User> listUsers=(List<User>)sessionFactory.getCurrentSession().createQuery("from User").list();
		return listUsers;
	}

}
