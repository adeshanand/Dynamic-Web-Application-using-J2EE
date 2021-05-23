package lms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class deleteBookServlet
 */
public class deleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteBookServlet() {
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
			String bid=request.getParameter("bid");
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con= DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
			
			String query1="select * from tbl_book where bid= ?";
			PreparedStatement pst1=con.prepareStatement(query1);
			String bidtm = bid.trim();
			pst1.setString(1, bidtm);
			ResultSet rs1=pst1.executeQuery();
			
			if(rs1.next()){
				String query2="select * from tbl_libraryIssueReturnBookRecord where return_date is null AND bid= ?";
				PreparedStatement pst2=con.prepareStatement(query2);
				pst2.setString(1, bidtm);
				ResultSet rs2=pst2.executeQuery();
				if(!(rs2.next())){
					String query="delete from tbl_book where bid=?";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1, bidtm);
					
					pst.executeUpdate();
					
					HttpSession session_delBook = request.getSession();
					Integer State = Integer.parseInt( session_delBook.getAttribute("State").toString());
					State = 3;
					session_delBook.setAttribute("State", State);
					request.getRequestDispatcher("deleteBook1.jsp").forward(request, response);
				}else{
					HttpSession session_delBook = request.getSession();
					Integer State = Integer.parseInt( session_delBook.getAttribute("State").toString());
					State = 2;
					session_delBook.setAttribute("State", State);
					request.getRequestDispatcher("deleteBook1.jsp").forward(request, response);
				}
			}else{
				HttpSession session_delBook = request.getSession();
				Integer State = Integer.parseInt( session_delBook.getAttribute("State").toString());
				State = 1;
				session_delBook.setAttribute("State", State);
				request.getRequestDispatcher("deleteBook1.jsp").forward(request, response);
			}
		}catch(Exception e){
	        e.printStackTrace();
	        System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET deleteBook");
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
