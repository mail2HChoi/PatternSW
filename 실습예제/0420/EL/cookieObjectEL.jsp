<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ǥ����� ���尴ü cookie</title>
</head>
<body>

<h2>ǥ����� ���尴ü cookie �̿�</h2>
<% 	
	Cookie c = new Cookie("userid", "gdhong");
	response.addCookie(c);
%>

\${cookie.userid.value} = ${cookie.userid.value}

<p><hr><h2>��ũ��Ʈ������ ���� Cookie �̿�</h2>
<%  
	Cookie[] cs = request.getCookies(); 

	if (!(cs == null)) {
		for (Cookie ck : cs) {
		if ( ck.getName().equals("userid") )
			out.println(ck.getValue());
		}
	}		
%>

</body>
</html>