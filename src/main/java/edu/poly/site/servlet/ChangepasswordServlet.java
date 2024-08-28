package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDAO;
import edu.poly.domain.ChangePasswordForm;

/**
 * Servlet implementation class ChangepasswordServlet
 */
@WebServlet("/site/Changepassword")
public class ChangepasswordServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   if (SessionUtils.isLogin(request)) {
          	 request.setAttribute("isLogin", true);
			}
		   
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_CHANGEPASSWORD_PAGE);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		String userID = SessionUtils.getLoginedusername(request);
		ChangePasswordForm form = new ChangePasswordForm();
		BeanUtils.populate(form, request.getParameterMap());
		request.setAttribute("userID", userID);
		
		if (!form.getConfirmPassword().equals(form.getPassword())) {
			request.setAttribute("error", "new password and new confirm password are not identical");
		}else {
			UserDAO dao = new UserDAO();
			dao.changePassword(form.getuserID(), form.getCurrentPassword(), form.getPassword());
			request.setAttribute("message", "Password is updated success");
		}
	} catch (Exception e) {
	e.printStackTrace();
	request.setAttribute("error", e.getMessage());// TODO: handle exception
	}
		
	PageInfoUser.prepareAndForward(request, response, PageType.SITE_CHANGEPASSWORD_PAGE);
	}

}
