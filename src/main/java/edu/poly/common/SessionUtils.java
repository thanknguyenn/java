package edu.poly.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
public static void add(HttpServletRequest request,String name,Object value) {
	HttpSession session = request.getSession();
	session.setAttribute(name, value);
}
public static Object get(HttpServletRequest request,String name) {
	HttpSession session = request.getSession();
	return session.getAttribute(name);
}
public static void invalidate(HttpServletRequest request) {
	HttpSession session = request.getSession();
	session.removeAttribute("userID");
	session.invalidate();
}
public static boolean isLogin(HttpServletRequest request) {
	return get(request, "userID") != null;
	
}
public static String getLoginedusername(HttpServletRequest request) {
	Object username = get(request,"userID");
	return username == null?null: username.toString();
} 
	
}

