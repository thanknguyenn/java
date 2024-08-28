package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.common.UploadUtils;
import edu.poly.dao.UserDAO;
import edu.poly.dao.VideoDAO;
import edu.poly.model.User;
import edu.poly.model.Video;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet({ "/admin/UserManagement", "/admin/UserManagement/create", "/admin/UserManagement/update",
		"/admin/UserManagement/delete", "/admin/UserManagement/reset", "/admin/UserManagement/edit" })
@MultipartConfig
public class UserManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		   // Lấy giá trị của pageNumber từ request
	    String pageNumberStr = request.getParameter("pageNumber");
	    int pageNumber = 1; // Giá trị mặc định cho pageNumber

	    // Kiểm tra xem pageNumberStr có null hay không và có thể chuyển đổi thành số nguyên không
	    if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
	        try {
	            pageNumber = Integer.parseInt(pageNumberStr);
	            
	        } catch (NumberFormatException e) {
	            // Xử lý nếu giá trị pageNumber không thể chuyển đổi thành số nguyên
	            e.printStackTrace();
	        }
	    }

	    request.setAttribute("pageNumber", pageNumber);

	    paginateList(request, pageNumber);
		String url = request.getRequestURI().toString();
	  
		if(url.contains("edit")) {
			edit(request,response);
			return;
		}
		if (url.contains("delete")) {
			delete(request,response);
			return;
		}
		if (url.contains("reset")) {
			reset(request,response);
			return;
		}
	    User user = new User();
	
		request.setAttribute("user", user);
		
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}

	
	private void paginateList(HttpServletRequest request, int pageNumber) {
	UserDAO dao = new UserDAO();
		    List<User> listuser = dao.findAll();

		    request.setAttribute("listuser", listuser);

		    int pageSize = 4; // Số lượng mục trên mỗi trang

		    int total = (int) Math.ceil((double) listuser.size() / pageSize);
		    request.setAttribute("totalPages", total);

		    // Tính toán vị trí bắt đầu và kết thúc của trang hiện tại
		 // Tính toán vị trí bắt đầu và kết thúc của trang hiện tại
		    int start = (pageNumber - 1) * pageSize;
		    int end = Math.min(start + pageSize, listuser.size());

		    // Kiểm tra xem start và end có vượt quá danh sách không
		    if (start >= listuser.size() || end > listuser.size() || start >= end) {
		        // Xử lý lỗi ở đây, ví dụ:
		        System.out.println("Lỗi: start hoặc end vượt quá danh sách hoặc start lớn hơn hoặc bằng end");
		    } else {
		        // Nếu không có lỗi, tiếp tục thực hiện
		        List<User> listusercount = dao.findPage(start, end);
		        request.setAttribute("listusercount", listusercount);
		    }
		
	}


	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String userID = request.getParameter("userID");
	        UserDAO dao =new  UserDAO();
			User user = dao.findById(userID);
	
	        request.setAttribute("user",user);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Error: " + e.getMessage());
	    }
	    PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		   String pageNumberStr = request.getParameter("pageNumber");
		    int pageNumber = 1; // Giá trị mặc định cho pageNumber

		    // Kiểm tra xem pageNumberStr có null hay không và có thể chuyển đổi thành số nguyên không
		    if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
		        try {
		            pageNumber = Integer.parseInt(pageNumberStr);
		            
		        } catch (NumberFormatException e) {
		            // Xử lý nếu giá trị pageNumber không thể chuyển đổi thành số nguyên
		            e.printStackTrace();
		        }
		    }

		    request.setAttribute("pageNumber", pageNumber);

		    paginateList(request, pageNumber);
		String url = request.getRequestURI().toString();
	if (url.contains("create")) {
		create(request,response);
		return;
	}
	if (url.contains("delete")) {
		delete(request,response);
		return;
	}
	if (url.contains("update")) {
		update(request,response);
		return;
	}
	if (url.contains("reset")) {
		reset(request,response);
		return;
	}

	}


	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    UserDAO dao =new  UserDAO();
	 		List<User> listuser = new ArrayList<User>();
	 		listuser = dao.findAll();
	 		request.setAttribute("listuser", listuser);
	 		 PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}



	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 UserDAO dao =new  UserDAO();
		try {
	    	 
	        String userID = request.getParameter("userID");
	      dao.delete(userID);
	        request.setAttribute("message", "User is deleted");
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Error: " + e.getMessage());
	    }
	  
	 	
	 		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}



	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		User user = new User();
			try {
			BeanUtils.populate(user, request.getParameterMap());
			
		UserDAO dao = new UserDAO();
			dao.update(user);
			request.setAttribute("user", user);
			request.setAttribute("message", "user is update");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error:"+e.getMessage());
			// TODO: handle exception
		}
			
	 		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
		}


	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		User user = new User();
		try {
		BeanUtils.populate(user, request.getParameterMap());
		
	UserDAO dao = new UserDAO();
		dao.insert(user);
		request.setAttribute("user", user);
		request.setAttribute("message", "user is inserted");
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("error", "Error:"+e.getMessage());
		// TODO: handle exception
	}
		
 		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
}
}
