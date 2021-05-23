/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.22
 * Generated at: 2014-05-25 05:13:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class bookWiseLibRecord2_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"tableCss.css\"/>\r\n");
      out.write("<title>BOOK EXCHANGE RECORD</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("td{width: 150px; text-align: center;}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "headerPage.jsp", out, false);
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "staffLibMenu.jsp", out, false);
      out.write("\r\n");
      out.write("<div style=\"text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;\">STUDENT'S LIBRARY RECORD</div>\r\n");
      out.write("<table cellspacing=\"0px\" class=\"spclTable\">\r\n");

Connection con = null;
		try {
			String bid=request.getParameter("bid");
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con= DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
			
			String query1="select * from tbl_book where bid= ?";
			PreparedStatement pst1=con.prepareStatement(query1);
			String bidtm = bid.trim();
			pst1.setString(1, bidtm);
			ResultSet rs1=pst1.executeQuery();
			
			if(rs1.next()){
				String query2="select * from tbl_libraryIssueReturnBookRecord where bid= ?";
				PreparedStatement pst2=con.prepareStatement(query2);
				bidtm = bid.trim();
				pst2.setString(1, bidtm);
				ResultSet rs2=pst2.executeQuery();
				if(rs2.next()){
			    	
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr><th>Student ID</th><th>Issued By</th><th>Issue Date</th><th>Return Date</th><th>Fine</th></tr>\r\n");
      out.write("\t\t\t\t\t");

					do{
					
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr><td>");
      out.print(rs2.getString("sid") );
      out.write("</td><td>");
      out.print(rs2.getString("issued_by") );
      out.write("</td><td>");
      out.print(rs2.getDate("issue_date") );
      out.write("</td><td>");
      out.print(rs2.getDate("return_date") );
      out.write("</td><td>");
      out.print(rs2.getInt("fine") );
      out.write("</td></tr>\r\n");
      out.write("\t\t\t\t\t");

					}while(rs2.next());
			    }
		}
			else{
				HttpSession session_bookWiseLibRecord = request.getSession();
				Integer State = Integer.parseInt( session_bookWiseLibRecord.getAttribute("State").toString());
				State = 1;
				session_bookWiseLibRecord.setAttribute("State", State);
				request.getRequestDispatcher("bookWiseLibRecord1.jsp").forward(request, response);
			}
		}catch(Exception e){
	        e.printStackTrace();
	        System.out.println("ERROR OCCURED WHILE PROCESSING THE SERVLET bookWiseLibRecordServlet");
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
