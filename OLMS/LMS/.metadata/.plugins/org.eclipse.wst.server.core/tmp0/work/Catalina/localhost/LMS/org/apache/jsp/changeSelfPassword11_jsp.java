/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.22
 * Generated at: 2014-05-25 06:39:55 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class changeSelfPassword11_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>CHANGE PASSWORD</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "headerPage.jsp", out, false);
      out.write('\r');
      out.write('\n');
 
String post_from_session = session.getAttribute("post").toString();
if(post_from_session.equals("Head of Library")){

      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "headLibMenu.jsp", out, false);
      out.write('\r');
      out.write('\n');
	
}else if(post_from_session.equals("Staff")){

      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "staffLibMenu.jsp", out, false);
      out.write('\r');
      out.write('\n');
	
}else if(post_from_session.equals("Administrator")){

      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "adminMenu.jsp", out, false);
      out.write('\r');
      out.write('\n');
	
}

      out.write('\r');
      out.write('\n');

HttpSession session_changeSelfPassword = request.getSession();
Integer State = Integer.parseInt( session_changeSelfPassword.getAttribute("State").toString());
if(State == 1){
	
      out.write("\r\n");
      out.write("\t<div style=\"margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;\">You have entered an incorrect password!</div>\r\n");
      out.write("\t");

}
if(State == 2){
	
      out.write("\r\n");
      out.write("\t<div style=\"margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;\">User Not Found!</div>\r\n");
      out.write("\t");

}
if(State == 3){
	
      out.write("\r\n");
      out.write("\t<div style=\"margin-right:20px; text-align:right; font-size:20px;font-weight: 600;padding-bottom: 25px;\">Password Changed Successfully!</div>\r\n");
      out.write("\t");

}

      out.write("\r\n");
      out.write("<div style=\"text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;\">CHANGE PASSWORD</div>\r\n");
      out.write("<form action=\"changeSelfPassword2.jsp\"  method=\"post\" >\r\n");
      out.write("<table align=\"center\">\r\n");
      out.write("<tr><td>Current Password</td><td><input type=\"password\" name=\"cpswd\" /></td></tr>\r\n");
      out.write("<tr><td><br/></td></tr>\r\n");
      out.write("<tr><td colspan=\"2\" align=\"center\"><input type=\"submit\" value=\"Continue\" /></td></tr>\r\n");
      out.write("</table>    \r\n");
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
