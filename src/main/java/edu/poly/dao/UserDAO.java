package edu.poly.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import edu.poly.model.User;
import edu.poly.model.Video;

public class UserDAO extends AbstractEntityDao<User> {
public UserDAO() {
		super(User.class);
		
	}
public void changePassword(String userID,String oldPassword,String newPassword)
		throws Exception {
	
	EntityManager em = JpaUtils.getEntityManager();
	EntityTransaction trans = em.getTransaction();
	String jpql="select u from User u where u.userID =:userID and u.password = :password";
	
	try {
		trans.begin();
		TypedQuery<User> query = em.createQuery(jpql,User.class);
		query.setParameter("userID", userID);
		query.setParameter("password", oldPassword);
		User user = query.getSingleResult();
		if (user==null) {
			throw new Exception("current password or userID are incorrect");
		}
		user.setPassword(newPassword);
		em.merge(user);
		trans.commit();
	} catch (Exception e) {
	trans.rollback();
	throw e;// TODO: handle exception
	}finally {
		em.close();
	}
	
}
public User FindByUsernameAndEmail(String userID, String email) {
    EntityManager em = JpaUtils.getEntityManager();
    EntityTransaction trans = em.getTransaction();
    User user = null;
    String jpql = "SELECT u FROM User u WHERE u.userID = :userID AND u.email = :email";
    
    try {
      
     

        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("userID", userID);
        query.setParameter("email", email);
        user = query.getSingleResult();
    
    } catch (NoResultException e) {
        System.out.println("User not found for userID: " + userID + " and email: " + email);
        
        e.printStackTrace();
    } catch (Exception e) {
     
        e.printStackTrace();
        System.out.println("Error: " + e.getMessage());
    } finally {
        em.close();
    }
    
    return user;
}
public List<User> findPage(int start, int pageSize) {
	    String jpql = "SELECT v FROM User v ORDER BY v.userID";
	    EntityManager em = JpaUtils.getEntityManager();
	    try {
	    	System.out.println("start " + start);
			System.out.println("pageSize " + pageSize);
	        TypedQuery<User> query = em.createQuery(jpql, User.class);
	     
	            query.setFirstResult(start); // Set the first result based on the start parameter
	         // Set the first result based on the start parameter
	        query.setMaxResults(pageSize); // Set the max results based on the pageSize parameter
	        List<User> list = query.getResultList();
	        System.out.println("Video render lần đầu : " + list);
	        return list; // Return the list of videos
	    } catch (Exception e) {
	    	e.printStackTrace();
	        System.out.println(e);
	    } finally {
	        em.close();
	    }
	    return new ArrayList<>(); // Return an empty list if an exception occurs
	}




}
