package lms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class submitBookServlet
 */
public class submitBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public submitBookServlet() {
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
			java.sql.Date return_date=new java.sql.Date(new java.util.Date().getTime());

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");

			String query2="select * from tbl_book where bid= ?";
			PreparedStatement pst2=con.prepareStatement(query2);
			String bidtm = bid.trim();
			pst2.setString(1, bidtm);
			ResultSet rs2=pst2.executeQuery();

			if(rs2.next()){
				String query3="select * from tbl_libraryIssueReturnBookRecord where return_date is null AND bid= ?";
				PreparedStatement pst3=con.prepareStatement(query3);
				pst3.setString(1, bidtm);
				ResultSet rs3 = pst3.executeQuery();

				if(rs3.next()){
					java.sql.Date issue_date= rs3.getDate("issue_date");
					long difference = return_date.getTime()- issue_date.getTime();
					Integer noOfDay = (int)(difference /(24*60*60*1000));
					Integer libFine = 0;
					if(noOfDay>15){
						libFine = (int) ((noOfDay-15)*0.5);
					}
					
					String query="update tbl_libraryIssueReturnBookRecord set return_date=?,fine=? where return_date is null AND bid= ?";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setDate(1, return_date);
					pst.setInt(2,libFine);
					pst.setString(3,bidtm);
					
					pst.executeUpdate();
										
					HttpSession session_submitBook = request.getSession();
					Integer State = Integer.parseInt( session_submitBook.getAttribute("State").toString());
					State = 3;
					session_submitBook.setAttribute("State", State);
					request.getRequestDispatcher("submitBook1.jsp").forward(request, response);
					
				}else{
					HttpSession session_submitBook = request.getSession();
					Integer State = Integer.parseInt( session_submitBook.getAttribute("State").toString());
					State = 2;
					session_submitBook.setAttribute("State", State);
					request.getRequestDispatcher("submitBook1.jsp").forward(request, response);
				}
			}else{
				HttpSession session_submitBook = request.getSession();
				Integer State = Integer.parseInt( session_submitBook.getAttribute("State").toString());
				State = 1;
				session_submitBook.setAttribute("State", State);
				request.getRequestDispatcher("submitBook1.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET submitBookServlet");
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
