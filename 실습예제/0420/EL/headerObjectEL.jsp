<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<html>
<head>
<title>ǥ����� ���尴ü header</title>
</head>
<body>

<h2>\${ header } : ��� </h2>
${ header } <p>

<hr>
\${ header['cookie'] } = ${ header['cookie'] } <p>
\${ header["connection"] } = ${ header["connection"] } <p>
\${ header["host"] } = ${ header["host"] } <p>
\${ header["accept-language"] } = ${ header["accept-language"] } <p>
\${ header["accept"] } = ${ header["accept"] } <p>
\${ header["user-agent"] } = ${ header["user-agent"] } <p>
\${ header["accept-encoding"] } = ${ header["accept-encoding"] } <p>
\${ header["ua-cpu"] } = ${ header["ua-cpu"] } <p>

</body>
</html>