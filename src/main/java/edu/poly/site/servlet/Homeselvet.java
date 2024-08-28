package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;

/**
 * Servlet implementation class Homeselvet
 */
@WebServlet("/site/Home")
public class Homeselvet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (SessionUtils.isLogin(request)) {
         	 request.setAttribute("isLogin", true);
			}
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_HOME_IMG);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_HOME_IMG);
	}

}
