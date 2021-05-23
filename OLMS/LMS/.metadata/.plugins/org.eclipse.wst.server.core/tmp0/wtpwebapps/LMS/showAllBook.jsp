<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="tableCss.css"/>
<title>SHOW ALL BOOK</title>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="headLibMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">BOOK RECORD</div>
<table cellspacing="0px" class="spclTable">
<%
Connection con = null;
try {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
    String query="select * from tbl_book";
    PreparedStatement pst=con.prepareStatement(query);
    ResultSet rs=pst.executeQuery();
    if(rs.next()){
    	%>
		<tr><th>Book ID</th><th>Subject</th><th>Book Name</th><th>Publication</th><th>Edition</th><th>Author</th><th>Price</th><th>Publishing Year</th><th>Vendor Name</th><th>Vendor Address</th><th>Bill No</th><th>Bill Date</th></tr>
		<%
		
		do{
		%>
		<tr><td><%=rs.getString("bid") %></td><td><%=rs.getString("subject") %></td><td><%=rs.getString("bname") %></td><td><%=rs.getString("publication") %></td><td><%=rs.getString("edition") %></td><td><%=rs.getString("author") %></td><td><%=rs.getInt("price") %></td><td><%=rs.getString("publishing_year") %></td><td><%=rs.getString("vendor_name") %></td><td><%=rs.getString("vendor_place") %></td><td><%=rs.getString("bill_no") %></td><td><%=rs.getDate("bill_date") %></td></tr>
		<%
		}while(rs.next());
		%>   
    <%	
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING showAllBook.jsp");
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

</table>
<jsp:include page="footerPage.jsp" />
</body>
</html>