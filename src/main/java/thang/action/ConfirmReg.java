package thang.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thang.model.User;
import thang.model.UserTemp;
import thang.userDAO.userDAO;

/**
 * Servlet implementation class ConfirmReg
 */
@WebServlet("/confirm")
public class ConfirmReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");response.setCharacterEncoding("utf-8");
		String number = request.getParameter("number");// get para mã xác nhận
		if (number ==null) {
			String url="/WEB-INF/jsp/confirmreg.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
			dispatcher.forward(request,response);
		} else {
			String email = request.getParameter("email"); // lấy email để dò với mã xác nhận trong bảng tạm
			for (UserTemp user : userDAO.getListTemp()) {
				if (email.equals(user.getEmail())) {
					if (number.equals(user.getCode())) { // mã xác nhận chính xác
						User userr = new User(user.getFname(),user.getLname(),user.getEmail(),user.getNumber(),user.getPassword());
						userDAO.insertUser(userr);
						userDAO.removeUser(user); System.out.println(userDAO.getListTemp().size());
						request.setAttribute("info1", user.getFname());
						request.setAttribute("info2", user.getEmail());
						
						
						for (int i = 0; i < userDAO.getListUser().size(); i++) { //test
							System.out.println(userDAO.getListUser().get(i).getEmail());
						}
						
						String url="/WEB-INF/jsp/success.jsp";//chuyển tới trang báo thành công
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
						dispatcher.forward(request,response);
						break;
					}else {   // mã xác nhận sai
						String url="/WEB-INF/jsp/register.jsp";  // chuyển tới trang đăng ký
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
						dispatcher.forward(request,response);
					}
					
				}
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
