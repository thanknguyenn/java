package edu.poly.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageInfoUser {
	public static Map<PageType, PageInfo> pageRoute = new HashMap<PageType, PageInfo>();

	static {
	
		pageRoute.put(PageType.SITE_HOME_PAGE, new PageInfo("Home page", "/site/home/home.jsp", null));
		pageRoute.put(PageType.SITE_LOGIN_PAGE, new PageInfo("Login page", "/site/users/login.jsp", null));
		pageRoute.put(PageType.SITE_REGISTRATION_PAGE, new PageInfo("Registration page", "/site/users/registration.jsp", null));
		pageRoute.put(PageType.SITE_CHANGEPASSWORD_PAGE, new PageInfo("Changepassword page", "/site/users/changepassword.jsp", null));
		pageRoute.put(PageType.SITE_FOGOTPASSWORD_SITE, new PageInfo("Fogotpassword page", "/site/users/fogotpassword.jsp", null));
		pageRoute.put(PageType.SITE_SHARE_PAGE, new PageInfo("Share page", "/site/videos/share.jsp", null));
		pageRoute.put(PageType.SITE_DETAIL_PAGE, new PageInfo("Detail page", "/site/videos/detail.jsp", null));
		pageRoute.put(PageType.SITE_FAVOURITE_PAGE, new PageInfo("Favourite page", "/site/videos/favourite.jsp", null));
		pageRoute.put(PageType.SITE_EDITPROFILE_PAGE, new PageInfo("Editprofile page", "/site/users/editprofile.jsp", null));
		pageRoute.put(PageType.SITE_HOME_IMG, new PageInfo("Home page", "/site/home.jsp", null));
	}

	public static void prepareAndForward(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException,IOException{
		PageInfo page = pageRoute.get(pageType);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/site/layout.jsp").forward(request, response);
	}

	private String title;
	private String contenUrl;
	private String scripUrl;





	public PageInfoUser(String title, String contenUrl, String scripUrl) {
		super();
		this.title = title;
		this.contenUrl = contenUrl;
		this.scripUrl = scripUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContenUrl() {
		return contenUrl;
	}

	public void setContenUrl(String contenUrl) {
		this.contenUrl = contenUrl;
	}

	public String getScripUrl() {
		return scripUrl;
	}

	public void setScripUrl(String scripUrl) {
		this.scripUrl = scripUrl;
	}

}
