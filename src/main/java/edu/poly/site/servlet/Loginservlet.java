package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDAO;
import edu.poly.domain.Loginform;
import edu.poly.model.User;

/**
 * Servlet implementation class Loginservlet
 */
@WebServlet("/site/Login")
public class Loginservlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = CookieUtils.get("userID", request);
	    if (username == null) {
	        PageInfoUser.prepareAndForward(request, response, PageType.SITE_LOGIN_PAGE);
	        return;
	    }
	
	  
	    System.out.println(username);
	    SessionUtils.add(request, "userID", username);
	    request.getRequestDispatcher("/site/Homepage").forward(request, response);       
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Loginform form = new Loginform();
			BeanUtils.populate(form, request.getParameterMap());
			UserDAO dao = new UserDAO();
			User user = dao.findById(form.getuserID());
			if (user != null && user.getPassword().equals(form.getPassword())) {
			
				if (user.getAdmin() ==true ) {
					
					  PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
				}else {
					SessionUtils.add(request, "userID", user.getUserID());
					if (form.isRemember()) {
						CookieUtils.add("userID", form.getuserID(), 24, response);
					}else {
						CookieUtils.add("userID", form.getuserID(), 0, response);
					}
					request.setAttribute("isLogin", true);
					request.setAttribute("name", user.getFullname());
					request.getRequestDispatcher("/site/Homepage").forward(request, response);
					
				}
			
				return;
			}
			
			request.setAttribute("error", "invalid user name or password");
			
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			// TODO: handle exception
		}
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_LOGIN_PAGE);
	}

}
