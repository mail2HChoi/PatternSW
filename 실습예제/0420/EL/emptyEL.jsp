<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ǥ����� ������ empty ����</title>
</head>
<body>

\${null} = ${ null } <p>
\${n} = ${n} <P>

\${empty null} = ${empty null} <p>
\${empty n} = ${empty n} <p>

\${param.user} = ${param.user} <P>
\${empty param.user} = ${empty param.user}

</body>
</html>