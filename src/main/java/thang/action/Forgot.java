package thang.action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import thang.userDAO.userDAO;


/**
 * Servlet implementation class Forgot
 * @param <HttpServletResponse>
 */
@WebServlet("/Forgot")
public class Forgot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forgot() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("gọi");
		String email = request.getParameter("email");
		String pass= request.getParameter("password");
		
		for (int i = 0; i < userDAO.getListUser().size(); i++) {
			if (userDAO.getListUser().get(i).getEmail().equals(email)) {
				userDAO.getListUser().get(i).setPassword(pass);
				request.setAttribute("info1", userDAO.getListUser().get(i).getEmail());
				request.setAttribute("info2", userDAO.getListUser().get(i).getPassword());
				String url="/WEB-INF/jsp/success.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
				dispatcher.forward(request,response);
			}
		}
		
	}

}