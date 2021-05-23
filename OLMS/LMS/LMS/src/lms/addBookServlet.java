package lms;

import java.io.IOException;
import java.text.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class addBookServlet
 */
public class addBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addBookServlet() {
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
			String choiceYN=request.getParameter("choiceYN");
			String qtyInString=request.getParameter("qty");

			if(choiceYN.equals("No")){
				if(bid!="" & subject!="" & bname!="" & publication!="" & edition!="" & author!="" & priceInString!="" & publishingyear!="" & dateInString!=""){
					Integer price=Integer.parseInt(request.getParameter("price"));
					if( price < 0 ){
						HttpSession session_addBook = request.getSession();
						Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
						State = 4;
						session_addBook.setAttribute("State", State);
						request.getRequestDispatcher("addBook1.jsp").forward(request, response);
					}else{
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						Date billdate = new Date(formatter.parse(dateInString).getTime());

						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						con = DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");


						String query="insert into tbl_book(bid,subject,bname,publication,edition,author,price,publishing_year,vendor_name,vendor_place,bill_no,bill_date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
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

						pst.executeUpdate();

						HttpSession session_addBook = request.getSession();
						Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
						State = 3;
						session_addBook.setAttribute("State", State);
						request.getRequestDispatcher("addBook1.jsp").forward(request, response);

					}
				}else{
					HttpSession session_addBook = request.getSession();
					Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
					State = 1;
					session_addBook.setAttribute("State", State);
					request.getRequestDispatcher("addBook1.jsp").forward(request, response);
				}
			}else if(choiceYN.equals("Yes") && qtyInString !=""){

				if(bid!="" & subject!="" & bname!="" & publication!="" & edition!="" & author!="" & priceInString!="" & publishingyear!="" & dateInString!=""){
					Integer price=Integer.parseInt(request.getParameter("price"));
					if( price < 0 ){
						HttpSession session_addBook = request.getSession();
						Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
						State = 4;
						session_addBook.setAttribute("State", State);
						request.getRequestDispatcher("addBook1.jsp").forward(request, response);
					}else{
						Integer qty=Integer.parseInt(request.getParameter("qty"));
						if( qty < 1 ){
							HttpSession session_addBook = request.getSession();
							Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
							State = 6;
							session_addBook.setAttribute("State", State);
							request.getRequestDispatcher("addBook1.jsp").forward(request, response);
						}else{
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							Date billdate = new Date(formatter.parse(dateInString).getTime());

							Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
							con = DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");


							Integer i=0;
							String bidtm = bid.trim();
							String subjecttm = subject.trim();
							String bnametm = bname.trim();
							String publicationtm = publication.trim();
							String authortm = author.trim();
														
							do{
								String query="insert into tbl_book(bid,subject,bname,publication,edition,author,price,publishing_year,vendor_name,vendor_place,bill_no,bill_date) values(?,?,?,?,?,?,?,?,?,?,?,?)";
								PreparedStatement pst=con.prepareStatement(query);

								bid = bidtm.concat("-"+ i.toString());

								pst.setString(1, bid);
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

								pst.executeUpdate();

								i++;	
							}while(i < qty);
							HttpSession session_addBook = request.getSession();
							Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
							State = 3;
							session_addBook.setAttribute("State", State);
							request.getRequestDispatcher("addBook1.jsp").forward(request, response);
						}
					}
				}else{
					HttpSession session_addBook = request.getSession();
					Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
					State = 1;
					session_addBook.setAttribute("State", State);
					request.getRequestDispatcher("addBook1.jsp").forward(request, response);
				}
			}else{
				if(bid!="" & subject!="" & bname!="" & publication!="" & edition!="" & author!="" & priceInString!="" & publishingyear!="" & dateInString!=""){

					HttpSession session_addBook = request.getSession();
					Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
					State = 5;
					session_addBook.setAttribute("State", State);
					request.getRequestDispatcher("addBook1.jsp").forward(request, response);
				}else{
					HttpSession session_addBook = request.getSession();
					Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
					State = 1;
					session_addBook.setAttribute("State", State);
					request.getRequestDispatcher("addBook1.jsp").forward(request, response);
				}
			}
		}catch(java.sql.SQLException e){
			System.out.println("ERROR OCCURED at line No 83 or 147 in addBook");
			HttpSession session_addBook = request.getSession();
			Integer State = Integer.parseInt( session_addBook.getAttribute("State").toString());
			State = 2;
			session_addBook.setAttribute("State", State);
			request.getRequestDispatcher("addBook1.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET addBookServlet");
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
