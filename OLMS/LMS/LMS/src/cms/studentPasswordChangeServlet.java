package cms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class studentPasswordChangeServlet
 */
public class studentPasswordChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentPasswordChangeServlet() {
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
			HttpSession session_sid= request.getSession();
			String sid_from_session = session_sid.getAttribute("sid").toString();
			String npswd = request.getParameter("npswd");
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
		    
		    String query="update tbl_user set password=? where uid=?";
		    PreparedStatement pst=con.prepareStatement(query); 
		    String sidtm = sid_from_session.trim();
		    String npswdtm = npswd.trim();
		    
		    pst.setString(1, npswdtm);
		    pst.setString(2, sidtm);
		      
		    if(pst.executeUpdate() == 1){
		    	HttpSession session_studentPasswordChange = request.getSession();
				Integer State = Integer.parseInt( session_studentPasswordChange.getAttribute("State").toString());
				State = 3;
				session_studentPasswordChange.setAttribute("State", State);
				request.getRequestDispatcher("studentPasswordChange1.jsp").forward(request, response);
		    }else{
		    	HttpSession session_studentPasswordChange = request.getSession();
				Integer State = Integer.parseInt( session_studentPasswordChange.getAttribute("State").toString());
				State = 4;
				session_studentPasswordChange.setAttribute("State", State);
				request.getRequestDispatcher("changeSelfPassword22.jsp").forward(request, response);
		    }
		}catch(java.sql.SQLException e){
			System.out.println("ERROR OCCURED at line No 50 in THE SERVLET studentPasswordChange");
			HttpSession session_studentPasswordChange = request.getSession();
			Integer State = Integer.parseInt( session_studentPasswordChange.getAttribute("State").toString());
			State = 5;
			session_studentPasswordChange.setAttribute("State", State);
			request.getRequestDispatcher("studentPasswordChange22.jsp").forward(request, response);
		}catch(Exception e){
	        e.printStackTrace();
	        System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET studentPasswordChange");
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
