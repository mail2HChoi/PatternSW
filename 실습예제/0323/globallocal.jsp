<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
	int global_cnt = 0; %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% int locan_cnt = 0;
		out.print("<br> local_cnt : ");
		out.print(++locan_cnt);
		
		out.print("<br>");
		
		out.print("<br> global_cnt : ");
		out.print(++global_cnt);
		%>
</body>
</html>