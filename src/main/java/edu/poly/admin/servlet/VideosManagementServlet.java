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
import edu.poly.common.PageType;
import edu.poly.common.UploadUtils;
import edu.poly.dao.VideoDAO;
import edu.poly.model.Video;

/**
 * Servlet implementation class VideosManagementServlet
 */
@WebServlet({ "/admin/VideosManagement", "/admin/VideosManagement/create", "/admin/VideosManagement/update",
		"/admin/VideosManagement/delete", "/admin/VideosManagement/reset", "/admin/VideosManagement/edit" })
@MultipartConfig
public class VideosManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// Kiểm tra nếu pageNumber là null

		// Lấy giá trị của pageNumber từ request
		String pageNumberStr = request.getParameter("pageNumber");
		int pageNumber = 1; // Giá trị mặc định cho pageNumber

		// Kiểm tra xem pageNumberStr có null hay không và có thể chuyển đổi thành số
		// nguyên không
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

		if (url.contains("edit")) {
			edit(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("reset")) {
			reset(request, response);
			return;
		}
		if (url.contains("create")) {
			create(request, response);
			return;
		}
		if (url.contains("update")) {
			update(request, response);
			return;
		}

		Video video = new Video();
		video.setPoster("images/OIP.jpg");
		request.setAttribute("video", video);
//		request.setAttribute("disable", "disabled");

		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void paginateList(HttpServletRequest request, int pageNumber) {
		VideoDAO dao = new VideoDAO();
		List<Video> listvideo = dao.findAll();

		request.setAttribute("listvideo", listvideo);

		int pageSize = 4; // Số lượng mục trên mỗi trang

		int total = (int) Math.ceil((double) listvideo.size() / pageSize);
		request.setAttribute("totalPages", total);

		// Tính toán vị trí bắt đầu và kết thúc của trang hiện tại
		// Tính toán vị trí bắt đầu và kết thúc của trang hiện tại
		int start = (pageNumber - 1) * pageSize;
		int end = Math.min(start + pageSize, listvideo.size());

		// Kiểm tra xem start và end có vượt quá danh sách không
		if (start >= listvideo.size() || end > listvideo.size() || start >= end) {
			// Xử lý lỗi ở đây, ví dụ:
			System.out.println("Lỗi: start hoặc end vượt quá danh sách hoặc start lớn hơn hoặc bằng end");
		} else {
			// Nếu không có lỗi, tiếp tục thực hiện
			List<Video> listvideocount = dao.findPage(start, end);
			request.setAttribute("listvideocount", listvideocount);
		}
	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String videoID = request.getParameter("videoID");
			VideoDAO dao = new VideoDAO();
			Video video = dao.findById(videoID);
			request.setAttribute("video", video);
		System.out.println(video.getHref());
			System.out.println("Đường dẫn của ảnh: " + video.getPoster());

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// Kiểm tra nếu pageNumber là null

		// Lấy giá trị của pageNumber từ request
		String pageNumberStr = request.getParameter("pageNumber");
		int pageNumber = 1; // Giá trị mặc định cho pageNumber

		// Kiểm tra xem pageNumberStr có null hay không và có thể chuyển đổi thành số
		// nguyên không
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
			create(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("update")) {
			update(request, response);
			return;
		}
		if (url.contains("reset")) {
			reset(request, response);
			return;
		}
		if (url.contains("edit")) {
			edit(request, response);
			return;
		}
//		request.setAttribute("disable", "disabled");
	}

	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video = new Video();
		video.setPoster("images/OIP.jpg");
		request.setAttribute("video", video);

		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String videoID = request.getParameter("videoID");
			VideoDAO dao = new VideoDAO();
			dao.delete(videoID);
			request.setAttribute("message", "Video is deleted");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

		PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	public String convertLinkYoutube(String href) {
		String embedLink = null;
		if (href.contains("watch?v=")) {
			// Tách chuỗi để lấy ra ID của video
			String videoId = href.split("watch\\?v=")[1];
			// Tạo liên kết nhúng bằng cách kết hợp với ID video
			embedLink = "https://www.youtube.com/embed/" + videoId;
		} else if (href.contains("youtu.be")) {
			// Nếu liên kết có dạng ngắn như youtu.be, tương tự tách chuỗi để lấy ID video
			String videoId = href.split("youtu.be/")[1];
			// Tạo liên kết nhúng
			embedLink = "https://www.youtube.com/embed/" + videoId;
		}
		return embedLink;
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    try {
	    	   Video video = new Video();
	           BeanUtils.populate(video, request.getParameterMap());

	        // Kiểm tra các trường bắt buộc trước khi cập nhật
	        if(video.getVideoID() == null || video.getVideoID().trim().isEmpty() || video.getHref() == null || video.getHref().trim().isEmpty()) {
	            // Nếu có trường bắt buộc rỗng, hiển thị thông báo lỗi và không thực hiện cập nhật
	            request.setAttribute("error", "Tên và liên kết là bắt buộc");
	        } else {
	        	
	           
	            String href = request.getParameter("href");
	            String embedLink = convertLinkYoutube(href);
	            video.setHref(embedLink);
	            String uploadedFileName = UploadUtils.processUploadField("cover", request, "/uploads", video.getPoster());
	            video.setPoster("../uploads/" + uploadedFileName);

	            VideoDAO dao = new VideoDAO();
	            dao.update(video);
	            request.setAttribute("video", video);
	            request.setAttribute("message", "Video is updated");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Error:" + e.getMessage());
	    }

	    PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    try {
	    	Video video = new Video();
	        BeanUtils.populate(video, request.getParameterMap());

	        // Kiểm tra các trường bắt buộc trước khi tạo mới
	        if(video.getVideoID() == null || video.getVideoID().trim().isEmpty() || video.getHref() == null || video.getHref().trim().isEmpty()) {
	            // Nếu có trường bắt buộc rỗng, hiển thị thông báo lỗi và không thực hiện tạo mới
	            request.setAttribute("error", "Tên và liên kết là bắt buộc");
	        } else {
	          
	            String href = request.getParameter("href");
	            String embedLink = convertLinkYoutube(href);
	            video.setHref(embedLink);
	            String uploadedFileName = UploadUtils.processUploadField("cover", request, "/uploads", video.getPoster());
	            video.setPoster("../uploads/" + uploadedFileName);


	            VideoDAO dao = new VideoDAO();
	            dao.insert(video);
	            request.setAttribute("video", video);
	            request.setAttribute("message", "Video is inserted");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "Error:" + e.getMessage());
	    }

	    PageInfo.prepareAndForward(request, response, PageType.VIDEO_MANAGEMENT_PAGE);
	}

}
