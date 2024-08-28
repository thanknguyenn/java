package edu.poly.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageInfo {

	public static Map<PageType, PageInfo> pageRoute = new HashMap<PageType, PageInfo>();

	static {
		pageRoute.put(PageType.USER_MANAGEMENT_PAGE, new PageInfo("User Management", "/admin/users/users.jsp", null));
		pageRoute.put(PageType.REPORT_MANAGEMENT_PAGE, new PageInfo("Reports Management", "/admin/reports/reports.jsp", null));
		pageRoute.put(PageType.VIDEO_MANAGEMENT_PAGE, new PageInfo("Videos Management", "/admin/videos/videos.jsp", null));
		pageRoute.put(PageType.HOME_PAGE, new PageInfo("Home Page", "/admin/home.jsp", null));
		
	
		
	}

	public static void prepareAndForward(HttpServletRequest request, HttpServletResponse response, PageType pageType)
			throws ServletException,IOException{
		PageInfo page = pageRoute.get(pageType);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/layout.jsp").forward(request, response);
	}

	private String title;
	private String contenUrl;
	private String scripUrl;





	public PageInfo(String title, String contenUrl, String scripUrl) {
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
