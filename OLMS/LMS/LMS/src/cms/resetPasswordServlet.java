package cms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 * Servlet implementation class resetPasswordServlet
 */
public class resetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		try{
			String vid = request.getParameter("vid");
			String rpswd = request.getParameter("rpswd");

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");

			String query1="select * from tbl_user where uid=?";
			PreparedStatement pst1=con.prepareStatement(query1);
			String vidtm = vid.trim();
			pst1.setString(1, vidtm);
			ResultSet rs1=pst1.executeQuery();

			if(rs1.next()){
				String query="update tbl_user set password=? where uid=?";
				PreparedStatement pst=con.prepareStatement(query);
				String rpswdtm = rpswd.trim();
				pst.setString(1, rpswdtm);
				pst.setString(2, vidtm);  
				
			    pst.executeUpdate();
			    
			    java.sql.Date reset_date=new java.sql.Date(new java.util.Date().getTime());
				String query3="insert into tbl_adminPasswordResetLog values(?,?)";
			    PreparedStatement pst3=con.prepareStatement(query3);
			    pst3.setString(1,vidtm);
			    pst3.setDate(2,reset_date);
			    pst3.executeUpdate();
				
				HttpSession session_resetPassword = request.getSession();
				Integer State = Integer.parseInt( session_resetPassword.getAttribute("State").toString());
				State = 3;
				session_resetPassword.setAttribute("State", State);
				request.getRequestDispatcher("resetPassword1.jsp").forward(request, response);
			}else{
				HttpSession session_resetPassword = request.getSession();
				Integer State = Integer.parseInt( session_resetPassword.getAttribute("State").toString());
				State = 1;
				session_resetPassword.setAttribute("State", State);
				request.getRequestDispatcher("resetPassword1.jsp").forward(request, response);
			}
			}catch(java.sql.SQLException e){
		    	HttpSession session_resetPassword = request.getSession();
				Integer State = Integer.parseInt( session_resetPassword.getAttribute("State").toString());
				State = 2;
				session_resetPassword.setAttribute("State", State);
				request.getRequestDispatcher("resetPassword1.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET resetPasswordServlet");
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
