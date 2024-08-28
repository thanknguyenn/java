package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDAO;
import edu.poly.model.User;

/**
 * Servlet implementation class EditprofileServlet
 */
@WebServlet("/site/Editprofile")
public class EditprofileServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String userID = SessionUtils.getLoginedusername(request);
	if (userID == null) {
		request.getRequestDispatcher("/site/Login").forward(request, response);
		return;
	}
	request.setAttribute("isLogin", true);
	try {
		UserDAO dao = new UserDAO();	
		User user = dao.findById(userID);
		request.setAttribute("user", user);
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("error", e.getMessage());
		// TODO: handle exception
	}
	PageInfoUser.prepareAndForward(request, response, PageType.SITE_EDITPROFILE_PAGE);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			
			String userID = SessionUtils.getLoginedusername(request);
			UserDAO dao = new UserDAO();	
			User olduser = dao.findById(userID);
			user.setAdmin(olduser.getAdmin());
			dao.update(user);
			request.setAttribute("message", "User profile updated !!");
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			// TODO: handle exception
			// TODO: handle exception
		}
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_EDITPROFILE_PAGE);
	}

}
