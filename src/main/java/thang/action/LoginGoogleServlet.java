package nmcnpm.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nmcnpm.common.GooglePojo;
import nmcnpm.common.GoogleUtils;

@WebServlet("/login-google")
public class LoginGoogleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginGoogleServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// nhận đoạn mã trả về từ google
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		} else {
			// đổi mã thành accessToken
			String accessToken = GoogleUtils.getToken(code);
			// dùng accessToken để truy cập các thông tin trong tài khoản google
			GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
			String id = googlePojo.getId();
			String email = googlePojo.getEmail();
			request.setAttribute("info1", id);
			request.setAttribute("info2", email);
			RequestDispatcher dis = request.getRequestDispatcher("/WEB-INF/jsp/success.jsp");
			dis.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
