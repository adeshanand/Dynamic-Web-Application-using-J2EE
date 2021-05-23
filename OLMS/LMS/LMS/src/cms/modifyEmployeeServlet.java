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
 * Servlet implementation class modifyEmployeeServlet
 */
public class modifyEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyEmployeeServlet() {
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
			String eid=request.getParameter("eid");
			String ename=request.getParameter("ename");
			String dateInString1 = request.getParameter("dob");
			String dateInString2 = request.getParameter("doj");
			String position=request.getParameter("position");
			String deptid=request.getParameter("deptid");
			String salaryInString=request.getParameter("salary");
			String contact=request.getParameter("contact");
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			
			if(eid!="" & ename!="" & dateInString1!="" & dateInString2!="" & position!="" & deptid!="" & salaryInString!="" & address!=""){
				Integer salary=Integer.parseInt(request.getParameter("salary"));
				if( salary < 0 ){
					HttpSession session_modifyEmployee = request.getSession();
					Integer State = Integer.parseInt( session_modifyEmployee.getAttribute("State").toString());
					State = 5;
					session_modifyEmployee.setAttribute("State", State);
					request.getRequestDispatcher("modifyEmployee33.jsp").forward(request, response);
				}else{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date dob = new Date(formatter.parse(dateInString1).getTime());
					Date doj = new Date(formatter.parse(dateInString2).getTime());

					HttpSession session2=request.getSession();
					String eidm = session2.getAttribute("eidm").toString();
					String eidmtm = eidm.trim();
					
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");

					String query="update tbl_employee set eid=?,ename=?,dob=?,doj=?,post=?,dept_id=?,salary=?,contact_no=?,email=?,address=? where eid=?";
					PreparedStatement pst=con.prepareStatement(query); 

					String eidtm = eid.trim();
					String enametm = ename.trim();
					String positiontm = position.trim();
					String deptidtm = deptid.trim();

					pst.setString(1, eidtm);
					pst.setString(2, enametm);
					pst.setDate(3, dob);
					pst.setDate(4, doj);
					pst.setString(5,positiontm);
					pst.setString(6,deptidtm);
					pst.setInt(7,salary);
					pst.setString(8,contact);
					pst.setString(9,email);
					pst.setString(10,address);
					pst.setString(11, eidmtm);

					pst.executeUpdate();
					
					if(!(eidtm.equals(eidmtm))){
						String query2="update tbl_user set uid=? where uid=?";
	        			PreparedStatement pst2=con.prepareStatement(query2); 
	        			pst2.setString(1, eidtm);
	        			pst2.setString(2, eidmtm);
	        			pst2.executeUpdate();
						
	    				String query3="update tbl_adminPasswordResetLog set uid=? where uid=?";
	        			PreparedStatement pst3=con.prepareStatement(query3); 
	        			pst3.setString(1, eidtm);
	        			pst3.setString(2, eidmtm);
	        			pst3.executeUpdate();
	    			}

					HttpSession session_modifyEmployee = request.getSession();
					Integer State = Integer.parseInt( session_modifyEmployee.getAttribute("State").toString());
					State = 6;
					session_modifyEmployee.setAttribute("State", State);
					request.getRequestDispatcher("modifyEmployee33.jsp").forward(request, response);
				}
			}else{
				HttpSession session_modifyEmployee = request.getSession();
				Integer State = Integer.parseInt( session_modifyEmployee.getAttribute("State").toString());
				State = 7;
				session_modifyEmployee.setAttribute("State", State);
				request.getRequestDispatcher("modifyEmployee33.jsp").forward(request, response);
			}
		}catch(java.sql.SQLException e){
			System.out.println("ERROR OCCURED at line No 81 in addStudentServlet");
			HttpSession session_modifyEmployee = request.getSession();
			Integer State = Integer.parseInt( session_modifyEmployee.getAttribute("State").toString());
			State = 8;
			session_modifyEmployee.setAttribute("State", State);
			request.getRequestDispatcher("modifyEmployee33.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET modifyEmployeeServlet");
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
