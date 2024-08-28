package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.FavouriteDAO;
import edu.poly.dao.VideoDAO;
import edu.poly.domain.FavouriteUserReport;
import edu.poly.domain.ShareUserReport;
import edu.poly.model.Favourite;
import edu.poly.model.Video;


/**
 * Servlet implementation class ReportsManagementServlet
 */
@WebServlet("/admin/ReportsManagement")


public class ReportsManagementServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		reportByFavourite(request,response);
		
		
			reportFavouriteUserByVideo(request,response);
		
		
			reportShareUser(request,response);
			
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

	
	private void reportShareUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		FavouriteDAO dao = new FavouriteDAO();
		String videotitle =request.getParameter("videotittle");
			List<ShareUserReport> list = dao.reportShareFriend(videotitle);
			request.setAttribute("share", list);
			
	} catch (Exception e) {
		e.printStackTrace();// TODO: handle exception
	}

	}


	private void reportByFavourite(HttpServletRequest request, HttpServletResponse response) {
		FavouriteDAO dao = new FavouriteDAO();
		var list =dao.reportByFavourite();
		request.setAttribute("reports", list);
		
	}


	private void reportFavouriteUserByVideo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String VideoUSerID = request.getParameter("videoUserID");
	        VideoDAO vdao = new VideoDAO();
	        List<Video> vlist = vdao.findAll();
	        if(VideoUSerID == null && vlist.size() > 0) {
	            VideoUSerID = vlist.get(0).getVideoID();
	        }
	        FavouriteDAO dao = new FavouriteDAO();
	        List<FavouriteUserReport> list = dao.reportFavouriteUserByVideo(VideoUSerID);
	        request.setAttribute("videoUserID", VideoUSerID);
	        request.setAttribute("vidList", vlist);
	        System.out.println(VideoUSerID);
	        System.out.println(vlist);
	        System.out.println(list);
	        request.setAttribute("favUsers", list);
	      
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "error:" + e.getMessage());
	    }
	  
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		reportByFavourite(request,response);
		
		
			reportFavouriteUserByVideo(request,response);
		
		
			reportShareUser(request,response);

		
    	PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);
	}

}
