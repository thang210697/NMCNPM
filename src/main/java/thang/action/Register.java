package thang.action;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thang.model.UserTemp;
import thang.userDAO.userDAO;
import thang.utilities.MailSender;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");response.setCharacterEncoding("utf-8");
		String url="/WEB-INF/jsp/register.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request,response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");response.setCharacterEncoding("utf-8");
		String fname= request.getParameter("fname");  // get thông số từ form
		String lname= request.getParameter("lname");
		String email= request.getParameter("email");
		String number= request.getParameter("number");
		String password= request.getParameter("password");
		
		Random random = new Random();     // tạo mã xác nhận 5 chư số
        int num = random.nextInt(100000);
        String code = String.format("%05d", num);
        UserTemp user = new UserTemp(fname,lname,email,number,password,code);
		// 1.2.1.1 insertTemp()
		userDAO.insertTemp(user);System.out.println(userDAO.getListTemp().size());
		// 1.2.1.2 send  - gửi mail xác nhận
		MailSender.send(email, "Please Confirm", code);  // gửi mail
		
		String url = "/confirm?email="+email;      // redirect trang xác nhận có chứa thông số là email
		response.sendRedirect(request.getContextPath() + url);
		
		
		
	}

}
