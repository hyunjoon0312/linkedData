package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import db.JdbcUtilUpload;
import jdk.nashorn.internal.scripts.JD;
import vo.MemberList;

public class DataListDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DataListDAO() {
	}

	public ArrayList<MemberList> getListAll(){
	
	ArrayList<MemberList> list = new ArrayList<MemberList>();
	
	try{
		con = JdbcUtilUpload.getUploadConnection();
		String sql = "Select * from uploadFile.UploadFileInfo";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			Timestamp time = rs.getTimestamp(1);
			String subject = rs.getString(2);
			String filename = rs.getString(3);
			String uploaderid = rs.getString(6);
			String uploadername = rs.getString(7);
			int nhis = rs.getInt(8);
			int stat = rs.getInt(9);
			MemberList member = new MemberList(time, subject, filename, uploaderid, uploadername, nhis, stat);
			list.add(member);
		}
		
	}catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}finally {
		if(rs!=null){JdbcUtilUpload.close(rs);}
		if(pstmt != null){JdbcUtilUpload.close(pstmt);}
		if(con != null){JdbcUtilUpload.close(con);}
	}
	return list;
	}

}
