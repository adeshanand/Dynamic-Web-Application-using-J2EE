package cms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class deleteStudentServlet
 */
public class deleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Connection con2 = null;
		try {
			String sid=request.getParameter("sid");
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con= DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
			
			String query1="select * from tbl_student where sid= ?";
			PreparedStatement pst1=con.prepareStatement(query1);
			String sidtm = sid.trim();
			pst1.setString(1, sidtm);
			ResultSet rs1=pst1.executeQuery();
			
			if(rs1.next()){
				con2= DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
				
				String query2="select * from tbl_libraryIssueReturnBookRecord where return_date is null AND sid= ?";
				PreparedStatement pst2=con2.prepareStatement(query2);
				pst2.setString(1, sidtm);
				ResultSet rs2=pst2.executeQuery();
				
				if(!(rs2.next())){
					String query="delete from tbl_student where sid=?";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1, sidtm);
					pst.executeUpdate();
					
					String query4="delete from tbl_user where uid=?";
					PreparedStatement pst4=con.prepareStatement(query4);
					pst4.setString(1, sidtm);
					pst4.executeUpdate();
					
					String query5="delete from tbl_libraryIssueReturnBookRecord where sid=?";
					PreparedStatement pst5=con2.prepareStatement(query5);
					pst5.setString(1, sidtm);
					pst5.executeUpdate();

					HttpSession session_delStudent = request.getSession();
					Integer State = Integer.parseInt( session_delStudent.getAttribute("State").toString());
					State = 1;
					session_delStudent.setAttribute("State", State);
					request.getRequestDispatcher("deleteStudent1.jsp").forward(request, response);
				}else{
					HttpSession session_delStudent = request.getSession();
					Integer State = Integer.parseInt( session_delStudent.getAttribute("State").toString());
					State = 3;
					session_delStudent.setAttribute("State", State);
					request.getRequestDispatcher("deleteStudent1.jsp").forward(request, response);
				}	
			}else{
				HttpSession session_delStudent = request.getSession();
				Integer State = Integer.parseInt( session_delStudent.getAttribute("State").toString());
				State = 2;
				session_delStudent.setAttribute("State", State);
				request.getRequestDispatcher("deleteStudent1.jsp").forward(request, response);
			}
		}catch(Exception e){
	        e.printStackTrace();
	        System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET deleteStudentServlet");
	        request.getRequestDispatcher("unExpectedError.jsp").forward(request, response);
	    }finally{
	    	try{
	    		if(con!=null) con.close();
	    		if(con2!=null) con2.close();
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
