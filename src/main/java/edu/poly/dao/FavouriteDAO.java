package edu.poly.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.poly.domain.FavouriteUserReport;
import edu.poly.domain.ShareUserReport;
import edu.poly.model.Favourite;
import edu.poly.model.Report;
import edu.poly.model.Video;


public class FavouriteDAO extends AbstractEntityDao<Favourite> {

	public FavouriteDAO() {
		super(Favourite.class);
		// TODO Auto-generated constructor stub
	}
	public List<FavouriteUserReport> reportFavouriteUserByVideo(String videoId){
		
		String jpql = "select new edu.poly.domain.FavouriteUserReport(f.user.userID,f.user.fullname,f.user.email,f.likeDate) from Favourite f where f.video.videoID = :videoId";
		EntityManager em = JpaUtils.getEntityManager();
		List<FavouriteUserReport> list = null ;
		try {
			TypedQuery<FavouriteUserReport> query = em.createQuery(jpql,FavouriteUserReport.class);
			query.setParameter("videoId", videoId);
			list = query.getResultList();
		
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		} finally {
			em.close();
		}
		return list;
	
	}
	public List<Report>  reportByFavourite()	{
		EntityManager em = JpaUtils.getEntityManager();
		String jpql = "SELECT new edu.poly.model.Report(f.video.tittle,count(f),max(f.likeDate),min(f.likeDate)) FROM edu.poly.model.Favourite f GROUP BY f.video.tittle";

		TypedQuery<Report> query = em.createQuery(jpql,Report.class);
		return query.getResultList();
		
		
	}
	public List<ShareUserReport> reportShareFriend(String videotitle ){
		EntityManager em = JpaUtils.getEntityManager();
		String jpql="SELECT new edu.poly.domain.ShareUserReport(s.user.userID,s.video.videoID,s.emails,s.sharedate) from Share s where s.video.tittle=:videotitle ";
		TypedQuery<ShareUserReport> query = em.createQuery(jpql,ShareUserReport.class);
		query.setParameter("videotitle", videotitle);
		return query.getResultList();
		
	}
	public List<Favourite> findPage(int start, int pageSize) {
	    String jpql = "SELECT v FROM Favourite v ORDER BY v.favouriteID";
	    EntityManager em = JpaUtils.getEntityManager();
	    try {
	    	System.out.println("start " + start);
			System.out.println("pageSize " + pageSize);
	        TypedQuery<Favourite> query = em.createQuery(jpql,Favourite.class);
	     
	            query.setFirstResult(start); // Set the first result based on the start parameter
	         // Set the first result based on the start parameter
	        query.setMaxResults(pageSize); // Set the max results based on the pageSize parameter
	        List<Favourite> list = query.getResultList();
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
