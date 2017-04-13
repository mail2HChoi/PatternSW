<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
 	Connection conn = null;
 	String sql = "SELECT * FROM member order by name";
 	PreparedStatement pstmt = null;
 	ResultSet rs = null;
 	
	try {
  		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jsptest");
  		conn = ds.getConnection();
  		
  		pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
			        	                   ResultSet.CONCUR_UPDATABLE);
  		rs = pstmt.executeQuery();
		
  		rs.last();
  		out.println(rs.getString(1)+","+rs.getString(3)+"<br>");
  		rs.first();
  		out.println(rs.getString(1)+","+rs.getString(3)+"<br>");
  		rs.absolute(3);
		out.println(rs.getString(1)+","+rs.getString(3)+"<br>");
	}catch(Exception e){
		out.println("<h3>데이터 가져오기에 실패하였습니다.</h3>");
		e.printStackTrace();
 	}finally{
 		try{
 			rs.close();
 			pstmt.close();
 			conn.close();
 		}
 		catch(Exception e){
 			e.printStackTrace();
 		}
 	}
%>
