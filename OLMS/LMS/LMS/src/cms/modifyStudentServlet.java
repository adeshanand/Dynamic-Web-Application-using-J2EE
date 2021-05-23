package cms;

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
 * Servlet implementation class modifyStudentServlet
 */
public class modifyStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyStudentServlet() {
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

    			HttpSession session2=request.getSession();
    			String sidm = session2.getAttribute("sidm").toString();
    			String sidmtm = sidm.trim();
    			
    			String sidtm = sid.trim();
    			String snametm = sname.trim();
    			String coursetm = course.trim();
    			String sessiontm = session.trim();

    			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    			con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
    			con1 = DriverManager.getConnection("jdbc:odbc:dsn_lmsavs"); 
    			
    			String query="update tbl_student set sid=?,sname=?,dob=?,doa=?,guardian_name=?,course=?,session=?,contact_no=?,email=?,address=? where sid=?";
    			PreparedStatement pst=con.prepareStatement(query); 

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
    			pst.setString(11, sidmtm);

    			pst.executeUpdate();
    			
    			if(!(sidtm.equals(sidmtm))){
    				String query2="update tbl_adminPasswordResetLog set uid=? where uid=?";
        			PreparedStatement pst2=con.prepareStatement(query2); 
        			pst2.setString(1, sidtm);
        			pst2.setString(2, sidmtm);
        			pst2.executeUpdate();
        			
        			String query3="update tbl_user set uid=? where uid=?";
        			PreparedStatement pst3=con.prepareStatement(query3); 
        			pst3.setString(1, sidtm);
        			pst3.setString(2, sidmtm);
        			pst3.executeUpdate();
        			
        			String query4="update tbl_libraryIssueReturnBookRecord set sid=? where sid=?";
        			PreparedStatement pst4=con1.prepareStatement(query4); 
        			pst4.setString(1, sidtm);
        			pst4.setString(2, sidmtm);
        			pst4.executeUpdate();
    			}

    			HttpSession session_modifyStudent = request.getSession();
    			Integer State = Integer.parseInt( session_modifyStudent.getAttribute("State").toString());
    			State = 6;
    			session_modifyStudent.setAttribute("State", State);
    			request.getRequestDispatcher("modifyStudent33.jsp").forward(request, response);


    		}else{
    			HttpSession session_modifyStudent = request.getSession();
    			Integer State = Integer.parseInt( session_modifyStudent.getAttribute("State").toString());
    			State = 7;
    			session_modifyStudent.setAttribute("State", State);
    			request.getRequestDispatcher("modifyStudent33.jsp").forward(request, response);
    		}
    	}catch(java.sql.SQLException e){
    		System.out.println("ERROR OCCURED at line No 81 in modifyStudentServlet");
    		HttpSession session_modifyStudent = request.getSession();
    		Integer State = Integer.parseInt( session_modifyStudent.getAttribute("State").toString());
    		State = 8;
    		session_modifyStudent.setAttribute("State", State);
    		request.getRequestDispatcher("modifyStudent33.jsp").forward(request, response);
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET modifyStudenteServlet");
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
