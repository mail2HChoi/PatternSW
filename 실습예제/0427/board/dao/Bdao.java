package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.Bdto;

public class Bdao {
	
	private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/jsptest");
        return ds.getConnection();
    }
	
	public ArrayList<Bdto> list() {
		ArrayList<Bdto> dtos = new ArrayList<Bdto>();
		Connection dbconn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			dbconn = getConnection();
			String sql = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from board order by bGroup desc, bStep asc";
			pstmt = dbconn.prepareStatement(sql);
			rs =pstmt.executeQuery();
			
			while(rs.next()){
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent =rs.getInt("bIndent");
				
				Bdto dto = new Bdto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(dbconn !=null) dbconn.close();
			}catch( Exception ee){
				ee.printStackTrace();
			}
			
		}
		return dtos;
	}
	
	private int getMaxnum(){
		Connection dbconn = null;
		PreparedStatement pstmt = null;
		int max = 0;
		try {
			dbconn = getConnection();
			
			String sql = "select max(bId) from board";
			pstmt = dbconn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			
			if(rs.next()){
				max=rs.getInt(1);
			}
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{			
				try {
				  if(pstmt !=null)pstmt.close();
				  if(dbconn !=null) dbconn.close();
				} catch (SQLException e) {					 
					e.printStackTrace();
				}			
		}		
		return max;
	}
	
	public void write(String bName, String bTitle, String bContent){
		Connection dbconn = null;
		PreparedStatement pstmt = null;
		int max = getMaxnum();
		
		try {
			dbconn = getConnection();
												
			String sql ="insert into board(bID, bName, bTitle, bContent, bDate, bHit,bGroup,bStep,bIndent) "
					+ "values(?,?,?,?,now(),0,?,0,0)";
			pstmt = dbconn.prepareStatement(sql);
			pstmt.setInt(1, max+1);
			pstmt.setString(2, bName);
			pstmt.setString(3, bTitle);
			pstmt.setString(4, bContent);
			pstmt.setInt(5, max+1);
			
			int n = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{			
				try {
				  if(pstmt !=null)pstmt.close();
				  if(dbconn !=null) dbconn.close();
				} catch (SQLException e) {					 
					e.printStackTrace();
				}			
		}		
	}//write
	
	public Bdto view(String sbId){
		
		plusHit(sbId);
		
		Bdto dto = null;
		Connection dbconn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			dbconn = getConnection();
			
			String sql = "select * from board where bId=?";
			pstmt = dbconn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sbId));
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent =rs.getInt("bIndent");
				
				dto = new Bdto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try{
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(dbconn !=null) dbconn.close();
			}catch( Exception ee){
				ee.printStackTrace();
			}
		}
		return dto;
	}//view	
	
	
	public void reply(String bid, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent){
		
		replyForm(bGroup, bStep);
		
		Connection dbconn = null;
		PreparedStatement pstmt = null;
		int max = getMaxnum();
		
		try {
			dbconn = getConnection();
			String sql = "insert into board(bId, bName, bTitle, bContent, bDate, bGroup, bStep, bIndent)"
					+ "values(?,?,?,?,now(),?,?,?)";
			pstmt = dbconn.prepareStatement(sql);
			pstmt.setInt(1, max+1);
			pstmt.setString(2, bName);
			pstmt.setString(3, bTitle);
			pstmt.setString(4, bContent);
			pstmt.setInt(5, Integer.parseInt(bGroup));
			pstmt.setInt(6, Integer.parseInt(bStep)+1);
			pstmt.setInt(7, Integer.parseInt(bIndent)+1);
			
			int n = pstmt.executeUpdate();
			              
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
		}
	} //reply
	
	private void replyForm(String sGroup, String sStep){
		
		Connection dbconn = null;
		PreparedStatement pstmt = null;
		
		try {
			dbconn = getConnection();
			String sql = "update board set bStep = bStep+1 where bGroup = ? and bStep > ?";
			pstmt = dbconn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sGroup));
			pstmt.setInt(2, Integer.parseInt(sStep));
			
			int n = pstmt.executeUpdate();
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
		
				try {
					if(pstmt !=null) pstmt.close();
					if(dbconn !=null) dbconn.close();
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			
		}
		
		
	}//replyform
	
	private void plusHit(String bId){
		Connection dbconn = null;
		PreparedStatement pstmt = null;
		
		try{
			dbconn = getConnection();
			String sql = "update board set bHit = bHit+1 where bId = ?";
			pstmt = dbconn.prepareStatement(sql);
			pstmt.setString(1, bId);
			
			int n = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(pstmt !=null) pstmt.close();
				if(dbconn !=null) dbconn.close();
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}
	}
}
