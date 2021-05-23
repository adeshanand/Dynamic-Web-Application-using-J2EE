package cms;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

/**
 * Servlet implementation class addEmployeeServlet
 */
public class addEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addEmployeeServlet() {
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
					HttpSession session_addEmployee = request.getSession();
					Integer State = Integer.parseInt( session_addEmployee.getAttribute("State").toString());
					State = 3;
					session_addEmployee.setAttribute("State", State);
					request.getRequestDispatcher("addEmployee1.jsp").forward(request, response);
				}else{
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date dob = new Date(formatter.parse(dateInString1).getTime());
					Date doj = new Date(formatter.parse(dateInString2).getTime());

					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");

					String query="insert into tbl_employee(eid,ename,dob,doj,post,dept_id,salary,contact_no,email,address) values(?,?,?,?,?,?,?,?,?,?)";
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

					pst.executeUpdate();
					
					String query2="insert into tbl_user values(?,?)";
					PreparedStatement pst2=con.prepareStatement(query2); 
					pst2.setString(1, eidtm);
					pst2.setString(2, eidtm);
					pst2.executeUpdate();

					HttpSession session_addEmployee = request.getSession();
					Integer State = Integer.parseInt( session_addEmployee.getAttribute("State").toString());
					State = 1;
					session_addEmployee.setAttribute("State", State);
					request.getRequestDispatcher("addEmployee1.jsp").forward(request, response);
				}
			}else{
				HttpSession session_addEmployee = request.getSession();
				Integer State = Integer.parseInt( session_addEmployee.getAttribute("State").toString());
				State = 2;
				session_addEmployee.setAttribute("State", State);
				request.getRequestDispatcher("addEmployee1.jsp").forward(request, response);
			}
		}catch(java.sql.SQLException e){
			System.out.println("ERROR OCCURED at line No 81 in addStudentServlet");
			HttpSession session_addEmployee = request.getSession();
			Integer State = Integer.parseInt( session_addEmployee.getAttribute("State").toString());
			State = 4;
			session_addEmployee.setAttribute("State", State);
			request.getRequestDispatcher("addEmployee1.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET addEmployeeServlet");
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
