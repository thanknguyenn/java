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
import edu.poly.model.Favourite;
import edu.poly.model.Video;

@WebServlet({"/site/Favourite", "/site/Favourite/delete"})
public class FavouriteServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (SessionUtils.isLogin(request)) {
         	 request.setAttribute("isLogin", true);
			}

        try {
            String pageNumberStr = request.getParameter("pageNumber");
            int pageNumber = 1; // Giá trị mặc định cho pageNumber

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

        String url = request.getRequestURI().toString();
        if (url.contains("delete")) {
            delete(request,response);
        }

        PageInfoUser.prepareAndForward(request, response, PageType.SITE_FAVOURITE_PAGE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void paginateList(HttpServletRequest request, int pageNumber) {
        FavouriteDAO dao = new FavouriteDAO();
        int pageSize = 8; // Số lượng yêu thích trên mỗi trang

        // Tính toán vị trí bắt đầu và kết thúc của trang hiện tại
        int start = (pageNumber - 1) * pageSize;
        int end = pageNumber * pageSize;

        // Truy vấn danh sách yêu thích cho trang hiện tại
        List<Favourite> listFavourite = dao.findPage(start, end);

        request.setAttribute("listFavourite", listFavourite);

        // Tính toán và đặt số trang
        List<Favourite> listvideo = dao.findAll();

	    request.setAttribute("listvideo", listvideo);
        // Tính toán và đặt số trang
        int total = (int) Math.ceil((double) listvideo.size() / pageSize);
	    request.setAttribute("totalPages", total);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String favouriteID = request.getParameter("favouriteID");
            FavouriteDAO dao = new FavouriteDAO();
            dao.delete(favouriteID);
            request.setAttribute("message", "Favourite video has been removed successfully");
            request.getRequestDispatcher("/site/Favourite").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
        }
    }
}
