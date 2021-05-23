package lms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class modifyBookServlet
 */
public class modifyBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyBookServlet() {
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
			String bid=request.getParameter("bid");
			String subject=request.getParameter("subject");
			String bname=request.getParameter("bname");
			String publication=request.getParameter("publication");
			String edition=request.getParameter("edition");
			String author=request.getParameter("author");
			String priceInString=request.getParameter("price");
			String publishingyear=request.getParameter("publishingyear");
			String vname=request.getParameter("vname");
			String vaddress=request.getParameter("vaddress");
			String billno=request.getParameter("billno");
			String dateInString = request.getParameter("billdate");
			
			if(bid!="" & subject!="" & bname!="" & publication!="" & edition!="" & author!="" & priceInString!="" & publishingyear!="" & dateInString!=""){
				Integer price=Integer.parseInt(request.getParameter("price"));
				if( price < 0 ){
					HttpSession session_modifyBook = request.getSession();
					Integer State = Integer.parseInt( session_modifyBook.getAttribute("State").toString());
					State = 5;
					session_modifyBook.setAttribute("State", State);
					request.getRequestDispatcher("modifyBook33.jsp").forward(request, response);
				}else{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date billdate = new Date(formatter.parse(dateInString).getTime());
					
					HttpSession session2=request.getSession();
					String bidm = session2.getAttribute("bidm").toString();
					String bidmtm = bidm.trim();

					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
					
					String query="update tbl_book set bid =?,subject=?,bname=?,publication=?,edition=?,author=?,price=?,publishing_year=?,vendor_name=?,vendor_place=?,bill_no=?,bill_date=? where bid=?";
					PreparedStatement pst=con.prepareStatement(query); 
					
					String bidtm = bid.trim();
					String subjecttm = subject.trim();
					String bnametm = bname.trim();
					String publicationtm = publication.trim();
					String authortm = author.trim();
					
					pst.setString(1, bidtm);
					pst.setString(2, subjecttm);
					pst.setString(3, bnametm);
					pst.setString(4, publicationtm);
					pst.setString(5, edition);
					pst.setString(6, authortm);
					pst.setInt(7, price);
					pst.setString(8, publishingyear);
					pst.setString(9, vname);
					pst.setString(10, vaddress);
					pst.setString(11, billno);
					pst.setDate(12, billdate);
					pst.setString(13, bidmtm);
					
					pst.executeUpdate();
					
					if(!(bidtm.equals(bidmtm))){
						String query2="update tbl_libraryIssueReturnBookRecord set bid=? where bid=?";
	        			PreparedStatement pst2=con.prepareStatement(query2); 
	        			pst2.setString(1, bidtm);
	        			pst2.setString(2, bidmtm);
	        			pst2.executeUpdate();
	    			}
					
					HttpSession session_modifyBook = request.getSession();
					Integer State = Integer.parseInt( session_modifyBook.getAttribute("State").toString());
					State = 6;
					session_modifyBook.setAttribute("State", State);
					request.getRequestDispatcher("modifyBook33.jsp").forward(request, response);
				}
			}else{
				HttpSession session_modifyBook = request.getSession();
				Integer State = Integer.parseInt( session_modifyBook.getAttribute("State").toString());
				State = 7;
				session_modifyBook.setAttribute("State", State);
				request.getRequestDispatcher("modifyBook33.jsp").forward(request, response);
			}
		}catch(java.sql.SQLException e){
			System.out.println("ERROR OCCURED at line No 92 in modifyBook");
			HttpSession session_modifyBook = request.getSession();
			Integer State = Integer.parseInt( session_modifyBook.getAttribute("State").toString());
			State = 8;
			session_modifyBook.setAttribute("State", State);
			request.getRequestDispatcher("modifyBook33.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET modifyBookServlet");
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
