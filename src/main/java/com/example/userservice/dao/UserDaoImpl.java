package com.example.userservice.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.userservice.model.User;

@Repository
public class UserDaoImpl implements UserDao{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
    private SessionFactory sessionFactory;

	/**
	 * The method register/signup a new user
	 */
	
	@Override
	public boolean signUpUser(User user) {
		boolean status = false;
		// Searching for existing user
		if(getUserDetails(user.getEmail()) != null) {
			LOGGER.info("signUpUser():-> User already created! email: {}",user.getEmail());
			return status;
		}
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Date date = new Date();
			String verifiedCode = getRandomCode();
			
			user.setCreatedDate(date);
			user.setLastLogin(date);
			user.setVerified(false);
			user.setVerifiedCode(verifiedCode);
			
			session.save(user);
			tx.commit();
			status = true;
			LOGGER.info("signUpUser(): New user created! email: {}",user.getEmail());
	 } catch (HibernateException e) {
        if (tx!=null) tx.rollback();
		status = false;
		LOGGER.error("signUpUser(): Exception in user signup! email: {}",user.getEmail());
        e.printStackTrace(); 
      } finally {
         session.close(); 
      }
		return status;
	}

	/**
	 * This method used to update a user data
	 */
	@Override
	public boolean updateUser(User user) {
		boolean status = false;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
			status = true;
			LOGGER.info("UserDaoImpl.updateUser(): user info updated: "+user);
		 } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         status = false;
	         LOGGER.error("Error: Update user", e); 
	      } finally {
	         session.close(); 
	      }
		return status;
	}

	/**
	 * This is used to find a user with email id
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public User getUserDetails(String email) {
		Session session = sessionFactory.openSession();
		Transaction tx =null;
		List<User> userList = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(User.class);
			cr.add(Restrictions.eq("email", email));
			userList = cr.list();
			tx.commit();
		}finally {
	         session.close(); 
	    }
		if(userList.size() != 0) {
			LOGGER.info("getUserDetails(): user: {}", userList.get(0));
			return userList.get(0);
		}
		LOGGER.info("getUserDetails(): user not found email: {}", email);
		return null;
	}
	
	private String getRandomCode() {
		String code ="test";
		return code;
	}
}
