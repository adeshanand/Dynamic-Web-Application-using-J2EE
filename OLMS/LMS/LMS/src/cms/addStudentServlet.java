package cms;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class addStudentServlet
 */
public class addStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addStudentServlet() {
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
			String sid=request.getParameter("sid");
			String sname=request.getParameter("sname");
			String dateInString1 = request.getParameter("dob");
			String dateInString2 = request.getParameter("doa");
			String gname=request.getParameter("gname");
			String course=request.getParameter("course");
			String session=request.getParameter("session");
			String contact=request.getParameter("contact");
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			
			if(sid!="" & sname!="" & dateInString1!="" & dateInString2!="" & gname!="" & course!="" & session!="" & address!=""){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date dob = new Date(formatter.parse(dateInString1).getTime());
				Date doa = new Date(formatter.parse(dateInString2).getTime());

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");

				String query="insert into tbl_student(sid,sname,dob,doa,guardian_name,course,session,contact_no,email,address) values(?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst=con.prepareStatement(query); 

				String sidtm = sid.trim();
				String snametm = sname.trim();
				String coursetm = course.trim();
				String sessiontm = session.trim();

				pst.setString(1, sidtm);
				pst.setString(2, snametm);
				pst.setDate(3, dob);
				pst.setDate(4, doa);
				pst.setString(5,gname);
				pst.setString(6,coursetm);
				pst.setString(7,sessiontm);
				pst.setString(8,contact);
				pst.setString(9,email);
				pst.setString(10,address);

				pst.executeUpdate();
				
				String query2="insert into tbl_user values(?,?)";
				PreparedStatement pst2=con.prepareStatement(query2); 
				pst2.setString(1, sidtm);
				pst2.setString(2, sidtm);
				pst2.executeUpdate();

				HttpSession session_addStudent = request.getSession();
				Integer State = Integer.parseInt( session_addStudent.getAttribute("State").toString());
				State = 1;
				session_addStudent.setAttribute("State", State);
				request.getRequestDispatcher("addStudent1.jsp").forward(request, response);
			}else{
				HttpSession session_addStudent = request.getSession();
				Integer State = Integer.parseInt( session_addStudent.getAttribute("State").toString());
				State = 2;
				session_addStudent.setAttribute("State", State);
				request.getRequestDispatcher("addStudent1.jsp").forward(request, response);
			}
		}catch(java.sql.SQLException e){
			System.out.println("ERROR OCCURED at line No 73 in addBookServlet");
			HttpSession session_addStudent = request.getSession();
			Integer State = Integer.parseInt( session_addStudent.getAttribute("State").toString());
			State = 3;
			session_addStudent.setAttribute("State", State);
			request.getRequestDispatcher("addStudent1.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET addStudentServlet");
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
