/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.22
 * Generated at: 2014-05-25 06:31:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class modifyStudent222_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"miscCss.css\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"tableCss.css\"/>\r\n");
      out.write("<title>MODIFY STUDENT</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "headerPage.jsp", out, false);
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "adminMenu.jsp", out, false);
      out.write("\r\n");
      out.write("<div style=\"text-align:center;font-size:20px;font-weight: 600;padding-bottom: 25px;\">SEARCH STUDENT</div>\r\n");
      out.write("<form method=\"post\" >\r\n");
      out.write("<table align=\"center\">\r\n");
      out.write("<tr><td><select name=\"choice\"><option value=\"sid\" >Student ID</option><option value=\"sname\" >Student Name</option><option value=\"course\" >Course</option><option value=\"session\" >Session</option></select></td><td><input type=\"text\" name=\"value\" /></td><td><input type=\"submit\" value=\"Search\" /></td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      out.write("<br />\r\n");
      out.write("<table cellspacing=\"0px\" class=\"spclTable\">\r\n");

Connection con = null;
try {
	String choice=request.getParameter("choice");
	String value=request.getParameter("value");
    
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
    
    String query="select * from tbl_student where "+ choice +" = ?";
    PreparedStatement pst=con.prepareStatement(query);
    String valuetm = value.trim();
    pst.setString(1, valuetm);
    ResultSet rs=pst.executeQuery();
    
    if(rs.next()){
    	
      out.write("\r\n");
      out.write("    \t<tr><th colspan=\"12\">SEARCH RESULTS</th></tr>\r\n");
      out.write("\t\t<tr><th>ID</th><th>Name</th><th>Date Of Birth</th><th>Date Of Admission</th><th>Guardian's Name</th><th>Course</th><th>Session</th><th>Contact No</th><th>E-mail</th><th>Address</th></tr>\r\n");
      out.write("\t\t");

		do{
		
      out.write("\r\n");
      out.write("\t\t<tr><td>");
      out.print(rs.getString("sid") );
      out.write("</td><td>");
      out.print(rs.getString("sname") );
      out.write("</td><td>");
      out.print(rs.getDate("dob") );
      out.write("</td><td>");
      out.print(rs.getDate("doa") );
      out.write("</td><td>");
      out.print(rs.getString("guardian_name") );
      out.write("</td><td>");
      out.print(rs.getString("course") );
      out.write("</td><td>");
      out.print(rs.getString("session") );
      out.write("</td><td>");
      out.print(rs.getString("contact_no") );
      out.write("</td><td>");
      out.print(rs.getString("email") );
      out.write("</td><td>");
      out.print(rs.getString("address") );
      out.write("</td></tr>\r\n");
      out.write("\t\t");

		}while(rs.next());
		
      out.write("   \r\n");
      out.write("    ");
	
    }else{
    	HttpSession session_modifyStudent = request.getSession();
		Integer State = Integer.parseInt( session_modifyStudent.getAttribute("State").toString());
		State = 2;
		session_modifyStudent.setAttribute("State", State);
    	request.getRequestDispatcher("modifyStudent22.jsp").forward(request, response);
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING modifyStudent222.jsp");
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

      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("<br />\r\n");
      out.write("<form action=\"modifyStudent3.jsp\" method=\"post\" >\r\n");
      out.write("<table align=\"center\">\r\n");
      out.write("<tr><td>Type Student ID</td><td><input type=\"text\" name=\"sidm\" placeholder=\"Case Sensitive\" title=\"Choose Student ID from the above table and type its ID here to modify record\" /></td><td><input type=\"submit\" value=\"Go\" /></td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footerPage.jsp", out, false);
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
