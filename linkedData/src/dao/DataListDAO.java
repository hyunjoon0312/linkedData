package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.MemberList;

public class DataListDAO {

	public MemberList getMemberList(){
		MemberList memberList = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://112.72.158.187:3306/uploadFile", "hyunjoon",
					"hyunjoon");
			System.out.println("UploadDB connect success");
			
			
			pstmt = con.prepareStatement("SELECT uploadtime, subject, filename, uploaderid, uploadername, nhis, stat FROM UploadFileInfo");
			rs = pstmt.executeQuery();
			if(rs.next()){
				memberList = new MemberList();
				memberList.setUploadtime(rs.getString("uploadtime"));
				memberList.setSubject(rs.getString("subject"));
				memberList.setFilename(rs.getString("filename"));
				memberList.setUploaderid(rs.getString("uploaderid"));
				memberList.setUploadername(rs.getString("uploadername"));
				memberList.setNhis(rs.getInt("nhis"));
				memberList.setStat(rs.getInt("stat"));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}            // PreparedStatement 객체 해제
			if(con != null) try{con.close();}catch(SQLException sqle){}            // PreparedStatement 객체 해제
			if(rs != null) try{rs.close();}catch(SQLException sqle){}            // PreparedStatement 객체 해제
			
		}
		return memberList;
	}
}
