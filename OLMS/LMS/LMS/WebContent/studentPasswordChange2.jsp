<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>CHANGE PASSWORD</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="staffLibMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">CHANGE PASSWORD</div>
<form action="studentPasswordChange" method="post" >
<table align="center">
<%
Connection con = null;
try {
	String sid = request.getParameter("sid");
	String cpswd =request.getParameter("cpswd");
	
	HttpSession session_sid= request.getSession();
	session_sid.setAttribute("sid", sid);
	
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
    
    String query="select * from tbl_user where uid=?";
    PreparedStatement pst=con.prepareStatement(query);
    String sidtm = sid.trim();
    pst.setString(1, sidtm);
    ResultSet rs=pst.executeQuery();
    if(rs.next()){
   		if(cpswd.equals(rs.getString("password"))){
   		%>
   			<tr><td>New Password</td><td><input type="password" name="npswd" /></td></tr>
   		<%
   		}else{
   			HttpSession session_studentPasswordChange = request.getSession();
   			Integer State = Integer.parseInt( session_studentPasswordChange.getAttribute("State").toString());
   			State = 2;
   			session_studentPasswordChange.setAttribute("State", State);
   			request.getRequestDispatcher("studentPasswordChange1.jsp").forward(request, response);
   		}
    }else{
    	HttpSession session_studentPasswordChange = request.getSession();
		Integer State = Integer.parseInt( session_studentPasswordChange.getAttribute("State").toString());
		State = 1;
		session_studentPasswordChange.setAttribute("State", State);
		request.getRequestDispatcher("studentPasswordChange1.jsp").forward(request, response);
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING studentPasswordChange2.jsp");
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
%>
<tr><td><br/></td></tr>
<tr><td colspan="2" align="center"><input type="submit" value="Change Password" /></td></tr>
</table>
</form>
<jsp:include page="footerPage.jsp" />
</body>
</html>