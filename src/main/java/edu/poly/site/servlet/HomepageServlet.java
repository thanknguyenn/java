package edu.poly.site.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.FavouriteDAO;
import edu.poly.dao.VideoDAO;
import edu.poly.model.Favourite;
import edu.poly.model.Video;

/**
 * Servlet implementation class HomepageServlet
 */
@WebServlet("/site/Homepage")
public class HomepageServlet extends HttpServlet {
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if (SessionUtils.isLogin(request)) {
          	 request.setAttribute("isLogin", true);
			}
		   
		  
	
		   
		 try {
	            String pageNumberStr = request.getParameter("pageNumber");
	            int pageNumber = 1; // Giá trị mặc định cho pageNumber
	            FavouriteDAO dao = new FavouriteDAO();
	           List<Favourite> listfav= dao.findAll();
	           request.setAttribute("listfav", listfav);
	            // Kiểm tra và chuyển đổi pageNumber từ chuỗi sang số nguyên
	            if (pageNumberStr != null && !pageNumberStr.isEmpty()) {
	                try {
	                    pageNumber = Integer.parseInt(pageNumberStr);
	                } catch (NumberFormatException e) {
	                    // Xử lý nếu không thể chuyển đổi pageNumber thành số nguyên
	                    e.printStackTrace();
	                }
	            }

	            request.setAttribute("pageNumber", pageNumber);

	            paginateList(request, pageNumber);
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", e.getMessage());
	            // Xử lý ngoại lệ chính xác ở đây, ví dụ: gửi thông báo lỗi đến giao diện người dùng
	        }

	        PageInfoUser.prepareAndForward(request, response, PageType.SITE_HOME_PAGE);
	    }

	    private void paginateList(HttpServletRequest request, int pageNumber) {
	        VideoDAO dao = new VideoDAO();
	        int pageSize = 8; // Số lượng video trên mỗi trang

	        // Tính toán vị trí bắt đầu và kết thúc của trang hiện tại
	        int start = (pageNumber - 1) * pageSize;
	        int end = pageNumber * pageSize;

	        // Truy vấn danh sách video cho trang hiện tại
	        List<Video> listvideocount = dao.findPage(start, end);

	        request.setAttribute("listvideocount", listvideocount);
	        List<Video> listvideo = dao.findAll();

		    request.setAttribute("listvideo", listvideo);
	        // Tính toán và đặt số trang
	        int total = (int) Math.ceil((double) listvideo.size() / pageSize);
		    request.setAttribute("totalPages", total);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }

}
