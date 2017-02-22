package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.JdbcUtilSTAT;
import db.JdbcUtilUpload;
import vo.MemberData;


/**
 * Servlet implementation class DatasendServlet
 */
@WebServlet("/datasend_stat")
public class DatasendServlet_STAT extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasendServlet_STAT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String filename = request.getParameter("filename");
		String uploadername = request.getParameter("uploadername");
		String str_stat_send = request.getParameter("stat_send");
		int stat_send = Integer.parseInt(str_stat_send);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("filename", filename);
		session.setAttribute("uploadername", uploadername);

		String refilename = null;
		refilename = filename.replace(".csv", "");
		
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		Connection con4 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		
		/*try{
			
			Class.forName("com.mysql.jdbc.Driver");
			con = JdbcUtilUpload.getUploadConnection();
					
			System.out.println("UploadDB connect success");
		
			stmt = con.createStatement();
			
			String sql = "SELECT nhis_send FROM uploadFile.UploadFileInfo where filename = "+filename;
		
			rs = stmt.executeQuery(sql);
		
			while(rs.next()){
				nhis_send = rs.getInt("nhis_send");
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtilUpload.close(con);
			
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JdbcUtilUpload.close(rs);
		}*/
		
		
		
		// stat_send == 0 이면 데이터 읽어와서 nhis 기관 DB에 저장
		
		if(stat_send == 0){
		
		try{
			
			con1 = JdbcUtilUpload.getUploadConnection();
					
			System.out.println("(1)UploadDB connect success");
		
		
			String sql = "UPDATE uploadFile.UploadFileInfo set stat_send = 1 where filename = "+"'"+filename+"'";
			
		
			pstmt1 = con1.prepareStatement(sql);
			pstmt1.executeUpdate();
			System.out.println("nhis_send 1로 변경");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtilUpload.close(con1);
			JdbcUtilUpload.close(pstmt1);
		}
		
		// ArrayList 만들어서 데이터 리스트형태로 저장
		
		ArrayList<MemberData> list = new ArrayList<MemberData>();
		int listsize = 0;
		
		
		try {
			con2 = JdbcUtilUpload.getUploadConnection();
			System.out.println("(2)UploadDB connect success");	
			
			
			
			String sql = "SELECT * FROM uploadFile."+uploadername+"_"+refilename;
			
			pstmt2 = con2.prepareStatement(sql);
			rs = pstmt2.executeQuery();
			
			while(rs.next()){
				int linkID = rs.getInt(1);
				String personID = rs.getString(2);
				MemberData memberData = new MemberData(linkID, personID);
				list.add(memberData);
			}
			listsize = list.size();
			System.err.println(list.size());
			System.out.println(uploadername+"_"+refilename+" 데이터 리스트로 불러옴");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(rs !=null){JdbcUtilUpload.close(rs);}
			if(con2 != null){JdbcUtilUpload.close(con2);}
			if(pstmt2 != null){JdbcUtilUpload.close(pstmt2);}
		}
		
		
		// 리스트형태로 불러온 데이터 저장할 테이블 새성
		
		
		try {
			con3 = JdbcUtilSTAT.getSTATConnection();
			System.out.println("(1)stat_send DB connect success");
			
			String sql = "CREATE TABLE "+uploadername+"_"+refilename+" (linkID int(20), personID VARCHAR(20), PRIMARY KEY(linkID))";
			
			pstmt3 = con3.prepareStatement(sql);
			pstmt3.executeUpdate();
			
			System.out.println(uploadername+"_"+refilename+"테이블 생성");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(con3 != null){JdbcUtilSTAT.close(con3);}
			if(pstmt3 != null){JdbcUtilSTAT.close(pstmt3);}
			
		}
		
		
		// 리스트형태로 불러온 데이터 저장
		
		try {
			con4 = JdbcUtilSTAT.getSTATConnection();
			System.out.println("(2)stat_send DB connect success");
			
			String sql = "INSERT INTO "+uploadername+"_"+refilename+" Values(?,?)";
			
			pstmt4 = con4.prepareStatement(sql);
			
				for(int i =0;i<listsize;i++){
				pstmt4.setInt(1, list.get(i).getLinkID());
				pstmt4.setString(2, list.get(i).getPersonID());
				pstmt4.addBatch();
			}
			
			int[] cnt = pstmt4.executeBatch();
			System.out.println(cnt.length+"row 성공");
			System.out.println(uploadername+"_"+refilename+" 데이터 저장 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(con4 != null){JdbcUtilSTAT.close(con4);}
			if(pstmt4 != null){JdbcUtilSTAT.close(pstmt4);}
			
		}
		
		
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("datasend_stat.jsp");
		rd.forward(request, response);
		
		
	}

}
