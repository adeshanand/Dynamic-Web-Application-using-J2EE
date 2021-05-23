package lms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 * Servlet implementation class issueBookServlet
 */
public class issueBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public issueBookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = null;
		Connection con1 = null;
		try {
			String sid=request.getParameter("sid");
			String bid=request.getParameter("bid");
			String confpswd=request.getParameter("confpswd");
			java.sql.Date issue_date=new java.sql.Date(new java.util.Date().getTime());
			Integer noba = 2;
			Integer nobi = 0;
			Boolean BookMatched = false;

			HttpSession session=request.getSession();
			String issued_by = null;

			if(session.getAttribute("uid")!= null){
				issued_by = session.getAttribute("uid").toString();
			}

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
			con1 = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");

			String query1="select * from tbl_student where sid= ?";
			PreparedStatement pst1=con1.prepareStatement(query1);
			String sidtm = sid.trim();
			pst1.setString(1, sidtm);
			ResultSet rs1=pst1.executeQuery();

			if(rs1.next()){
				String query2="select * from tbl_book where bid= ?";
				PreparedStatement pst2=con.prepareStatement(query2);
				String bidtm = bid.trim();
				pst2.setString(1, bidtm);
				ResultSet rs2=pst2.executeQuery();

				if(rs2.next()){
					String BookName = rs2.getString("bname");
					String BookSubject = rs2.getString("subject");
					String BookPublication = rs2.getString("publication");
					String BookAuthor = rs2.getString("author");
					String query3="select * from tbl_libraryIssueReturnBookRecord where return_date is null AND bid= ?";
					PreparedStatement pst3=con.prepareStatement(query3);
					pst3.setString(1, bidtm);
					ResultSet rs3 = pst3.executeQuery();

					if(!(rs3.next())){
						String query4="select * from tbl_user where uid= ?";
						PreparedStatement pst4=con1.prepareStatement(query4);
						pst4.setString(1, sidtm);
						ResultSet rs4 = pst4.executeQuery();
						rs4.next();

						if(confpswd.equals( rs4.getString("password"))){
							String query5="select * from tbl_libraryIssueReturnBookRecord where return_date is null AND sid= ?";
							PreparedStatement pst5=con.prepareStatement(query5);
							pst5.setString(1, sidtm);
							ResultSet rs5 = pst5.executeQuery();
							while(rs5.next()) nobi++;

							if(nobi < noba){
								String query6="select * from tbl_libraryIssueReturnBookRecord where return_date is null AND sid= ?";
								PreparedStatement pst6=con.prepareStatement(query6);
								pst6.setString(1, sidtm);
								ResultSet rs6 = pst6.executeQuery();

								while(rs6.next()){ 
									String BookID = rs6.getString("bid");
									String query7="select * from tbl_book where bid= ?";
									PreparedStatement pst7=con.prepareStatement(query7);
									pst7.setString(1, BookID);
									ResultSet rs7 = pst7.executeQuery();
									rs7.next();
									
									if( BookName.equals(rs7.getString("bname")) & BookSubject.equals(rs7.getString("subject")) & BookPublication.equals(rs7.getString("publication")) & BookAuthor.equals(rs7.getString("author")) ){
										BookMatched = true;
									}
								}

								if(BookMatched==false){
									String query="insert into tbl_libraryIssueReturnBookRecord(sid,bid,issued_by,issue_date) values(?,?,?,?)";
									PreparedStatement pst=con.prepareStatement(query);
									pst.setString(1,sidtm);
									pst.setString(2,bidtm);
									pst.setString(3,issued_by);
									pst.setDate(4, issue_date);

									pst.executeUpdate();

									HttpSession session_issueBook = request.getSession();
									Integer State = Integer.parseInt( session_issueBook.getAttribute("State").toString());
									State = 7;
									session_issueBook.setAttribute("State", State);
									request.getRequestDispatcher("issueBook1.jsp").forward(request, response);
								}else{
									HttpSession session_issueBook = request.getSession();
									Integer State = Integer.parseInt( session_issueBook.getAttribute("State").toString());
									State = 6;
									session_issueBook.setAttribute("State", State);
									request.getRequestDispatcher("issueBook1.jsp").forward(request, response);
								}

							}else{
								HttpSession session_issueBook = request.getSession();
								Integer State = Integer.parseInt( session_issueBook.getAttribute("State").toString());
								State = 5;
								session_issueBook.setAttribute("State", State);
								request.getRequestDispatcher("issueBook1.jsp").forward(request, response);
							}
						}else{
							HttpSession session_issueBook = request.getSession();
							Integer State = Integer.parseInt( session_issueBook.getAttribute("State").toString());
							State = 4;
							session_issueBook.setAttribute("State", State);
							request.getRequestDispatcher("issueBook1.jsp").forward(request, response);
						}
					}else{
						HttpSession session_issueBook = request.getSession();
						Integer State = Integer.parseInt( session_issueBook.getAttribute("State").toString());
						State = 3;
						session_issueBook.setAttribute("State", State);
						request.getRequestDispatcher("issueBook1.jsp").forward(request, response);
					}
				}else{
					HttpSession session_issueBook = request.getSession();
					Integer State = Integer.parseInt( session_issueBook.getAttribute("State").toString());
					State = 2;
					session_issueBook.setAttribute("State", State);
					request.getRequestDispatcher("issueBook1.jsp").forward(request, response);
				}
			}else{
				HttpSession session_issueBook = request.getSession();
				Integer State = Integer.parseInt( session_issueBook.getAttribute("State").toString());
				State = 1;
				session_issueBook.setAttribute("State", State);
				request.getRequestDispatcher("issueBook1.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET issueBookServlet");
			request.getRequestDispatcher("unExpectedError.jsp").forward(request, response);
		}finally{
			try{
				if(con!=null) con.close();
				if(con1!=null) con1.close();
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
