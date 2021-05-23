<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HEAD OF LIBRARY</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="headLibMenu.jsp" />
<div style="height:150px; vertical-align:middle; text-align: center; background-color:black; color:aqua; ">
<h5 style="padding-top:10px;">NOTICE BOARD</h5>
<%
Connection con = null;
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");

			String query="select * from tbl_msg where msg_date=(select max(msg_date) from tbl_msg)";
			PreparedStatement pst=con.prepareStatement(query); 
			ResultSet rs=pst.executeQuery();
			rs.next();
			%>
			<div style="text-align:left; font-size:21px; margin-left: 21px;" ><%=rs.getString("msg")%></div>
			<%
		}catch(java.sql.SQLException e){
			System.out.println("ERROR OCCURED in headLibHome.jsp");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR OCCURED WHILE PROCESSING headLibHome.jsp");
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
</div>
<jsp:include page="footerPage.jsp" />
</body>
</html>