package cms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class deleteEmployeeServlet
 */
public class deleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteEmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			String eid=request.getParameter("eid");

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con= DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");

			String query1="select * from tbl_employee where eid= ?";
			PreparedStatement pst1=con.prepareStatement(query1);
			String eidtm = eid.trim();
			pst1.setString(1, eidtm);
			ResultSet rs1=pst1.executeQuery();
			if(rs1.next()){
				String query="delete from tbl_employee where eid=?";
				PreparedStatement pst=con.prepareStatement(query);
				pst.setString(1, eidtm);
				pst.executeUpdate(); 
				
				String query2="delete from tbl_user where uid=?";
				PreparedStatement pst2=con.prepareStatement(query2);
				pst2.setString(1, eidtm);
				pst2.executeUpdate();
				
				HttpSession session_delEmployee = request.getSession();
				Integer State = Integer.parseInt( session_delEmployee.getAttribute("State").toString());
				State = 1;
				session_delEmployee.setAttribute("State", State);
				request.getRequestDispatcher("deleteEmployee1.jsp").forward(request, response);
			}else{
				HttpSession session_delEmployee = request.getSession();
				Integer State = Integer.parseInt( session_delEmployee.getAttribute("State").toString());
				State = 2;
				session_delEmployee.setAttribute("State", State);
				request.getRequestDispatcher("deleteEmployee1.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET deleteEmployeeServlet");
			request.getRequestDispatcher("unExpectedError.jsp").forward(request, response);
		}finally{
			try{
				if(con!=null) con.close();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("CONNECTION TO THE DATABASE COULD NOT BE TERMINATED.");
				request.getRequestDispatcher("unExpectedError.jsp").forward(request, response);
			}
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
