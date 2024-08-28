package edu.poly.site.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageInfoUser;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.ShareDAO;
import edu.poly.dao.UserDAO;
import edu.poly.model.Share;
import edu.poly.model.User;
import edu.poly.model.Video;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/site/Share")
public class ShareServlet extends HttpServlet {
	
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  if (SessionUtils.isLogin(request)) {
	          	 request.setAttribute("isLogin", true);
				}
		  
		  String videoId = request.getParameter("videoId");
	        if (videoId == null) {
	            // Log the redirection
	            log("Redirecting to Homepage because videoId is null.");
	            response.sendRedirect("/ASMJAVA4/site/Homepage");
	            return;
	        }
	        request.setAttribute("videoId", videoId);
	        PageInfoUser.prepareAndForward(request, response, PageType.SITE_SHARE_PAGE);
	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String videoId = request.getParameter("videoId");
			
			if (videoId == null) {
				request.setAttribute("error", "VideoId is null");
			} else {
				

				String name = "teoteohu@gmail.com";
				String password = "vxks fwcx gtpi yejk";
				Properties props = new Properties();
				props.setProperty("mail.smtp.auth", "true");
				props.setProperty("mail.smtp.starttls.enable", "true");

				props.setProperty("mail.smtp.host", "smtp.gmail.com");
				props.setProperty("mail.smtp.port", "587");
				props.put("mail.smtp.ssl.protocols", "TLSv1.2");

				Session session = Session.getInstance(props, new Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

						return new javax.mail.PasswordAuthentication(name, password);
					}
				});
				MimeMessage message = new MimeMessage(session);
				try {
					message.setFrom(new InternetAddress(name));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
					message.setSubject("Share Favourite Video", "utf-8");
					message.setText(
							"Dear Ms/Mr \n"+
							"The video is more interesting and i want to share with you \n"+
							"Please click the link \n"+String.format("<a href=''> view video</a>", videoId)+
						    "Regards\n Adminitrator",
							"utf-8");
					message.setReplyTo(message.getFrom());
					Transport.send(message);
					ShareDAO dao = new ShareDAO();
					Share share = new Share();
					share.setEmails(emailAddress);
					String userID = SessionUtils.getLoginedusername(request);
					User user = new User();
					user.setUserID(userID);
					share.setUser(user);
					share.setSharedate(new Date());
					Video video = new Video();
					video.setVideoID(videoId);
					share.setVideo(video);
					dao.insert(share);
					request.setAttribute("message", "Video link has been sent");
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("error", "Không gửi được email" + e.getMessage());
				} catch (MessagingException e) {
					   e.printStackTrace();
					    request.setAttribute("error", "Lỗi khi gửi email: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			   e.printStackTrace();
			    request.setAttribute("error", "Lỗi không xác định: " + e.getMessage());
		}
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_SHARE_PAGE);
	}

}
