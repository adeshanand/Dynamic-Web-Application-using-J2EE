<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="miscCss.css"/>
<title>MODIFY STUDENT</title>
<script type="text/javascript" language="javascript">
<!-- 
function popup(){
	alert('IF STUDENT ID IS MODIFIED, IT WILL BE MODIFIED EVERYWHERE! ')
}
// -->
</script>
<script type="text/javascript" language="javascript">
<!-- 
function openWinCourses(){
	w1=window.open('courseNames.html',height=120,'Courses',"toolbar=no, menubar=no, width=250px, height=551px, resizable=no");
}
// -->
</script>
</head>
<body>
<jsp:include page="headerPage.jsp" />
<jsp:include page="adminMenu.jsp" />
<%
HttpSession session_modifyStudent = request.getSession();
Integer State = Integer.parseInt( session_modifyStudent.getAttribute("State").toString());
if(State == 7){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Fill all mandatory fields first. </div>
	<%
}
if(State == 8){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Student ID you entered is not available. </div>
	<%
}
if(State == 6){
	%>
	<div style="margin-right:20px; text-align:right; font-size:20px;font-weight: 600;padding-bottom: 25px;"> Student Modified Successfully! </div>
	<%
	request.getRequestDispatcher("modifyStudent11.jsp").forward(request, response);
}
if(State == 5){
	%>
	<div style="margin-left: 20px; text-align: left; font-style: italic; font-weight: 600;"> Salary can never less than 0. </div>
	<%
}
%>
<div style="text-align:center;font-size:20px;font-weight: 600;padding-bottom: 50px;">MODIFY STUDENT</div>
<form name="StudFrm" action="modifyStudent" method="post" >
<table align="center">
<%
Connection con = null;
try {
	HttpSession session2=request.getSession();
	String sidm = session2.getAttribute("sidm").toString();
    
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    con = DriverManager.getConnection("jdbc:odbc:dsn_cmsavs");
    
    String query="select * from tbl_student where sid = ?";
    PreparedStatement pst=con.prepareStatement(query);
    String sidmtm = sidm.trim();
    pst.setString(1, sidmtm);
    ResultSet rs=pst.executeQuery();
    
    if(rs.next()){
    	%>
		<tr><td>ID*</td><td><input onclick="popup()" type="text" name="sid" value="<%=rs.getString("sid") %>" /></td></tr>
		<tr><td>Name*</td><td><input type="text" name="sname" value="<%=rs.getString("sname") %>" /></td></tr>
		<tr><td>Date of Birth*</td><td><input type="date" name="dob" value="<%=rs.getDate("dob") %>" /></td></tr>
		<tr><td>Date of Admission*</td><td><input type="date" name="doa" value="<%=rs.getDate("doa") %>" /></td></tr>
		<tr><td>Guardian's Name*</td><td><input type="text" name="gname" value="<%=rs.getString("guardian_name") %>" /></td></tr>
		<tr><td>Course*</td><td><input type="text" name="course" value="<%=rs.getString("course") %>" ondblclick="openWinCourses()"/></td></tr>
		<tr><td>Session*</td><td><input type="text" name="session" value="<%=rs.getString("session") %>" /></td></tr>
		<tr><td>Contact Number</td><td><input type="text" name="contact" value="<%=rs.getString("contact_no") %>" /></td></tr>
		<tr><td>E-mail</td><td><input type="text" name="email" value="<%=rs.getString("email") %>" /></td></tr>
		<tr><td>Address*</td><td><input type="text" name="address" value="<%=rs.getString("address") %>" /></td></tr>  
    <%	
    }else{

    	session_modifyStudent = request.getSession();
		State = Integer.parseInt( session_modifyStudent.getAttribute("State").toString());
		State = 3;
		session_modifyStudent.setAttribute("State", State);
    	request.getRequestDispatcher("modifyStudent22.jsp").forward(request, response);
    }
    }catch(Exception e){
        e.printStackTrace();
        System.out.println("ERROR OCCURED WHILE PROCESSING modifyStudent33.jsp");
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
<tr><td colspan="2" align="center"><input type="submit" value="Modify" /></td></tr>
</table>
</form>
<span>* mandatory</span>
<jsp:include page="footerPage.jsp" />
</body>
</html>