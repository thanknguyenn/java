package edu.poly.site.servlet;

import java.io.IOException;
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
import edu.poly.dao.UserDAO;
import edu.poly.model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/site/Fogotpassword")
public class FogotPasswordServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (SessionUtils.isLogin(request)) {
         	 request.setAttribute("isLogin", true);
			}
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_FOGOTPASSWORD_SITE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String emailAddress = request.getParameter("email");
			String userID = request.getParameter("userID");
			UserDAO dao = new UserDAO();
			User user = dao.FindByUsernameAndEmail(userID, emailAddress);
			if (user == null) {
				request.setAttribute("error", "Username or email are incorrect");
			} else {
				String UserPassword = user.getPassword();

				String name = "nnn@gmail.com";
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
					message.setSubject("Dear: " + userID, "utf-8");
					message.setText(
							"Your password is " + UserPassword + ".Please remember it !!\n" + "Regards\n Adminitrator",
							"utf-8");
					message.setReplyTo(message.getFrom());
					Transport.send(message);
					request.setAttribute("message", "Password has been sent in your mail");
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("error", "Không gửi được email" + e.getMessage());
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					request.setAttribute("message", "" + e.getMessage());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		PageInfoUser.prepareAndForward(request, response, PageType.SITE_FOGOTPASSWORD_SITE);

	}

}
