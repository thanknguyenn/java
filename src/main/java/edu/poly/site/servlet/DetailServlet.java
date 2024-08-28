package edu.poly.site.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.VideoDAO;
import edu.poly.model.Video;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/site/Detail")
public class DetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (SessionUtils.isLogin(request)) {
         	 request.setAttribute("isLogin", true);
			}
		 
		  
			  VideoDAO dao = new VideoDAO();
			  String videoID = request.getParameter("videoID");
			  Video video = dao.findById(videoID);
			String href = video.getHref();
			request.setAttribute("href", href);
			  request.setAttribute("video", video);
			  List<Video> listvideo= dao.findAll();
			  request.setAttribute("listvideo", listvideo);
			  String videoId = request.getParameter("videoID");
		        if (videoId == null) {
		            // Log the redirection
		            log("Redirecting to Homepage because videoId is null.");
		            response.sendRedirect("/ASMJAVA4/site/Homepage");
		            return;
		        }
		        request.setAttribute("videoId", videoId);
		
		
		
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_DETAIL_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}

}
