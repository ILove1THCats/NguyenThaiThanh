package Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		Connection conn = MySQLConntUtils.getMySQLConnection(request);
		String errorString = null;
		List<Product> list = new ArrayList<Product>();
		
		try {
			
			Connection conn = MySQLConntUtils.getMySQLConnection();
			
			list = DBUtils.queryProduct(conn);
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("productlist", list);
		
		//Forward sang /WEB-INF/views/productListView.jsp
		request.getRequestDispatcher("/ProductList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
