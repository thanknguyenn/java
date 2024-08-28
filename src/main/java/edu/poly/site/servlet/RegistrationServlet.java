package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.dao.UserDAO;
import edu.poly.model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/site/Registration")
public class RegistrationServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_REGISTRATION_PAGE);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
		
		BeanUtils.populate(user, request.getParameterMap());
		UserDAO dao = new UserDAO();
		dao.insert(user);
		request.getRequestDispatcher("/site/Login").forward(request, response);
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("error", e.getMessage());
		// TODO: handle exception
	}
		request.setAttribute("user", user);
		if (!response.isCommitted()) {
		    PageInfoUser.prepareAndForward(request, response, PageType.SITE_REGISTRATION_PAGE);
		}

	}

}
