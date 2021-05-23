package cms;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;


/**
 * Servlet implementation class loginSrevlet
 */
public class loginSrevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginSrevlet() {
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
			String uid =request.getParameter("uid");
			String pswd =request.getParameter("pswd");
	        
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	        con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
	        
	        String query="select * from tbl_user where uid=?";
	        PreparedStatement pst=con.prepareStatement(query);
	        pst.setString(1, uid);
	        ResultSet rs=pst.executeQuery();
	        
	        if(rs.next()){
	        	if(uid.equals(rs.getString("uid"))){
	        		if(pswd.equals(rs.getString("password"))){
	        			String query1="select * from tbl_employee where eid=?";
	        	        PreparedStatement pst1=con.prepareStatement(query1);
	        	        pst1.setString(1, uid);
	        	        ResultSet rs1 = pst1.executeQuery();
	        	        rs1.next();
	        			
	        	        String ename = rs1.getString("ename");
	        			String post= rs1.getString("post");
        				
	        			HttpSession session=request.getSession();
        				session.setAttribute("uid", uid);
        				session.setAttribute("ename",ename);
        				session.setAttribute("post",post);
	        			
        				if(post.equals("Head of Library")){
	        				request.getRequestDispatcher("headLibHome.jsp").forward(request, response);
	        			}
	        			else if(post.equals("Staff")){
	        				request.getRequestDispatcher("staffLibHome.jsp").forward(request, response);
	        			}
	        			else if(post.equals("Administrator")){
	        				request.getRequestDispatcher("adminHome.jsp").forward(request, response);
	        			}
	        		
	        		}else{
	        			HttpSession session_login = request.getSession();
	        			Integer errorState = Integer.parseInt( session_login.getAttribute("errorState").toString());
	        			errorState = 2;
	        			session_login.setAttribute("errorState", errorState);
	        			request.getRequestDispatcher("login.jsp").forward(request, response);
	        		}
	        	}else{
	        		HttpSession session_login = request.getSession();
	        		Integer errorState = Integer.parseInt( session_login.getAttribute("errorState").toString());
	        		errorState = 1;
	        		session_login.setAttribute("errorState", errorState);
	        		request.getRequestDispatcher("login.jsp").forward(request, response);
	        	}
	        }else{
	        	HttpSession session_login = request.getSession();
        		Integer errorState = Integer.parseInt( session_login.getAttribute("errorState").toString());
        		errorState = 1;
        		session_login.setAttribute("errorState", errorState);
        		request.getRequestDispatcher("login.jsp").forward(request, response);
	        }
	    }catch(Exception e){
	        e.printStackTrace();
	        System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET loginSrevlet");
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
