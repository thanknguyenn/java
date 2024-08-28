package edu.poly.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.poly.model.Video;

public class VideoDAO extends AbstractEntityDao<Video> {

	public VideoDAO() {
		super(Video.class);
		// TODO Auto-generated constructor stub
	}

	public List<Video> findPage(int start, int pageSize) {
	    String jpql = "SELECT v FROM Video v ORDER BY v.videoID";
	    EntityManager em = JpaUtils.getEntityManager();
	    try {
	    	System.out.println("start " + start);
			System.out.println("pageSize " + pageSize);
	        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
	     
	            query.setFirstResult(start); // Set the first result based on the start parameter
	         // Set the first result based on the start parameter
	        query.setMaxResults(pageSize); // Set the max results based on the pageSize parameter
	        List<Video> list = query.getResultList();
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
