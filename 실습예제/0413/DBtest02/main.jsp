<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String id=null;

	if (session.getAttribute("id")!=null){
		id=(String)session.getAttribute("id");
	}
%>
<html>
<head>
<title>회원관리 시스템 메인 페이지</title>
</head>
<body>
<h3><%=id %> 로 로그인하셨습니다.</h3>
<% 
if (session.getAttribute("id")!=null) {
	if (id.equals("admin"))
   {
%>
	<a href="member_list.jsp">관리자모드 접속(회원 목록 보기)</a>
	</body>
</html>
<% }
} else
{
%>
	<script type="text/javascript">
	location.href='loginForm.jsp';
	</script>
	<% }%>

