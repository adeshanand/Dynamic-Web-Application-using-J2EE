<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SEARCH BOOK</title>
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="headLibMenu.jsp" />
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">BOOK DETAILS</div>
<%
Connection con = null;
try {
	String bids=request.getParameter("bids");
    
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_lmsavs");
    
    String query="select * from tbl_book where bid = ?";
    PreparedStatement pst=con.prepareStatement(query);
    String bidstm = bids.trim();
    pst.setString(1, bidstm);
    ResultSet rs=pst.executeQuery();
    
    if(rs.next()){
    	%>
    	<table cellspacing="0px" class="spclTable2">
		<tr><td>Book ID</td><td><%=rs.getString("bid") %> </td></tr>
		<tr><td>Subject</td><td><%=rs.getString("subject") %> </td></tr>
		<tr><td>Book Name</td><td><%=rs.getString("bname") %> </td></tr>
		<tr><td>Publication</td><td><%=rs.getString("publication") %></td></tr>
		<tr><td>Edition</td><td><%=rs.getString("edition") %></td></tr>
		<tr><td>Author</td><td><%=rs.getString("author") %></td></tr>
		<tr><td>Price</td><td><%=rs.getInt("price") %></td></tr>
		<tr><td>Publishing Year</td><td><%=rs.getString("publishing_year") %></td></tr>
		<tr><td>Vendor Name</td><td><%=rs.getString("vendor_name") %> </td></tr>
		<tr><td>Vendor Address</td><td><%=rs.getString("vendor_place") %></td></tr>
		<tr><td>Bill No</td><td><%=rs.getString("bill_no") %></td></tr>
		<tr><td>Bill Date</td><td><%=rs.getDate("bill_date") %></td></tr>
		<%
    	String query2="select * from tbl_libraryIssueReturnBookRecord where return_date is null AND bid= ?";
		PreparedStatement pst2=con.prepareStatement(query2);
		pst2.setString(1, bidstm);
		ResultSet rs2=pst2.executeQuery();
		if((rs2.next())){
		%>
			<tr><td>Issue Status</td><td>Issued</td></tr>
        <%	
        }else{
        %>
    		<tr><td>Issue Status</td><td>Not Issued</td></tr> 
        <%	
		}
		%>
		</table>
	<%	
    }else{
    	HttpSession session_searchBook = request.getSession();
		Integer State = Integer.parseInt(session_searchBook.getAttribute("State").toString());
		State = 3;
		session_searchBook.setAttribute("State", State);
    	request.getRequestDispatcher("bookFound11.jsp").forward(request, response);
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING bookFound2.jsp");
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
<jsp:include page="footerPage.jsp" />
</body>
</html>