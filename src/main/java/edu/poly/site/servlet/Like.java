package edu.poly.site.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.FavouriteDAO;
import edu.poly.model.Favourite;
import edu.poly.model.User;
import edu.poly.model.Video;

/**
 * Servlet implementation class Like
 */
@WebServlet("/site/Like")
public class Like extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   if (!SessionUtils.isLogin(request)) {
				response.sendRedirect("/ASMJAVA4/site/Login");
				return;
			}
			  String page = request.getParameter("page");
			  String videoId = request.getParameter("videoId");
		        if (videoId == null) {
		            // Log the redirection
		            log("Redirecting to Homepage because videoId is null.");
		            response.sendRedirect("/ASMJAVA4/site/Homepage");
		            return;
		        }
		        try {
					FavouriteDAO dao = new FavouriteDAO();
					Favourite favourite = new Favourite();
				Video video = new  Video();
				video.setVideoID(videoId);
					
					favourite.setVideo(video);
					String username= SessionUtils.getLoginedusername(request);
					User user = new User();
					user.setUserID(username);
					favourite.setUser(user);
					favourite.setLikeDate(new Date());
					dao.insert(favourite);
					request.setAttribute("message", "Video is added to Favourite");
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("error", e.getMessage());
				}
		        if(page == null) {
		        	page="/site/Homepage";
		        	
		        }
		        request.getRequestDispatcher(page).forward(request, response);
		      
		       
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
