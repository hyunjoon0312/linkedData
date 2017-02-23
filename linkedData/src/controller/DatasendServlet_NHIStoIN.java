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

import db.JdbcUtil;
import db.JdbcUtilNHIS;
import db.JdbcUtilUpload;
import vo.MemberData;
import vo.MemberSend;


/**
 * Servlet implementation class DatasendServlet
 */
@WebServlet("/J_NHIS/datasend_NHIStoIN")
public class DatasendServlet_NHIStoIN extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasendServlet_NHIStoIN() {
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
		
		String tableName = request.getParameter("tableName");
		String str_send_indexer = request.getParameter("send_indexer");
		int send_indexer = Integer.parseInt(str_send_indexer);
		
		
		
		
		
		HttpSession session = request.getSession();
		session.setAttribute("tableName", tableName);

		
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		Connection con4 = null;
		Connection con5 = null;
		Connection con6 = null;
		Connection con7 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
	
		if(send_indexer == 0){
		
		try{
			
			con1 = JdbcUtil.getConnection();
					
			System.out.println("(1)NHIS DB connect success");
		
		
			String sql = "UPDATE nhis_take_data.nhis_take_data_info set send_indexer = 1 where tableName = "+"'"+tableName+"'";
			
		
			pstmt1 = con1.prepareStatement(sql);
			pstmt1.executeUpdate();
			System.out.println("send_indexer 1로 변경");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con1 != null){JdbcUtil.close(con1);}
			if(pstmt1 != null){JdbcUtil.close(pstmt1);}
		}
//-------------------------------------------여기부터 --------------------------------------------
		//NHIS DB에서 기관 식별자랑 주민번호 읽어와서 INDEXER DB에 넣어주자.
		
		// ArrayList 만들어서 데이터 리스트형태로 저장
		
/*		ArrayList<MemberData> list = new ArrayList<MemberData>();
		int listsize = 0;
		
		
		try {
			con2 = JdbcUtil.getConnection();
			System.out.println("(2)NHIS DB connect success");	
			
			
			
			String sql = "SELECT * FROM nhis_take_data."+tableName;
			
			pstmt2 = con2.prepareStatement(sql);
			rs1 = pstmt2.executeQuery();
			
			while(rs1.next()){

				String personID = rs1.getString(1);
				MemberData memberData = new MemberData(personID);
				list.add(memberData);
			}
			listsize = list.size();
			System.out.println(tableName+" 데이터 리스트로 불러옴");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(rs1 !=null){JdbcUtil.close(rs1);}
			if(con2 != null){JdbcUtil.close(con2);}
			if(pstmt2 != null){JdbcUtil.close(pstmt2);}
		}
		
		
		// 리스트형태로 불러온 데이터 저장할 테이블 생성
		
		
		try {
			con3 = JdbcUtil.getConnection();
			System.out.println("(1)NECA INDEXER DB connect success");
			
			//String sql = "CREATE TABLE "+uploadername+"_"+refilename+" (linkID int(20), personID VARCHAR(20), PRIMARY KEY(linkID))";
			String sql = "CREATE TABLE "+uploadername+"_"+refilename+" (personID VARCHAR(20), PRIMARY KEY(personID))";
			
			pstmt3 = con3.prepareStatement(sql);
			pstmt3.executeUpdate();
			
			System.out.println(uploadername+"_"+refilename+"테이블 생성");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(con3 != null){JdbcUtilNHIS.close(con3);}
			if(pstmt3 != null){JdbcUtilNHIS.close(pstmt3);}
			
		}
		
		
		// 리스트형태로 불러온 데이터 저장
		
		try {
			con4 = JdbcUtilNHIS.getNHISConnection();
			System.out.println("(2)nhis_send DB connect success");
			
			//나중에 내 방식대로 할 때 필요한 코드
			//String sql = "INSERT INTO "+uploadername+"_"+refilename+" Values(?,?)";
			String sql = "INSERT INTO "+uploadername+"_"+refilename+" Values(?)";
			
			pstmt4 = con4.prepareStatement(sql);
			
			for(int i =0;i<listsize;i++){
				
				
				pstmt4.setString(1, list.get(i).getPersonID());
				pstmt4.addBatch();
			
			
			//나중에 내 방식대로 할때 필요한 코드
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
			if(con4 != null){JdbcUtilNHIS.close(con4);}
			if(pstmt4 != null){JdbcUtilNHIS.close(pstmt4);}
			
		}
		
		
		}
		
		//-------- 각 기관에 데이터 전송하였는지 여부 확인 -----------------------
		
		try {
			con5 = JdbcUtilUpload.getUploadConnection();
			System.out.println("(3)UploadDB connect success");	
			
			
			
			String sql = "SELECT nhis_send, stat_send FROM uploadFile.UploadFileInfo where filename = "+"'"+filename+"'";
			
			
			pstmt5 = con5.prepareStatement(sql);
			rs2 = pstmt5.executeQuery();
			
			while(rs2.next()){
				memberSend = new MemberSend();
				memberSend.setNhis_send(rs2.getInt(1)); 
				memberSend.setStat_send(rs2.getInt(2));
	
			}
		
			System.out.println(filename+" 데이터 기관 전송 여부 확인");
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if(rs2 !=null){JdbcUtilUpload.close(rs2);}
			if(con5 != null){JdbcUtilUpload.close(con5);}
			if(pstmt5 != null){JdbcUtilUpload.close(pstmt5);}
		}
		

		//----------- 각 기관에 데이터 전송 여부에 따른 진행 ----------
		
		if(memberSend.getNhis_send() == 1 && memberSend.getStat_send() == 1){
			
			try{
				
				con6 = JdbcUtilUpload.getUploadConnection();
						
				System.out.println("(4)UploadDB connect success");
			
			
				String sql = "UPDATE uploadFile.UploadFileInfo set send_ok = 1 where filename = "+"'"+filename+"'";
				
			
				pstmt6 = con6.prepareStatement(sql);
				pstmt6.executeUpdate();
				System.out.println("send_ok 1로 변경");
			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(con6 != null){JdbcUtilUpload.close(con6);}
				if(pstmt6 != null){JdbcUtilUpload.close(pstmt6);}
			}
			
			
			
			
			try{
				
				con7 = JdbcUtilUpload.getUploadConnection();
						
				System.out.println("(5)UploadDB connect success");
			
			
				String sql = "DROP TABLE uploadFile."+uploadername+"_"+refilename;
				
			
				pstmt7 = con7.prepareStatement(sql);
				pstmt7.executeUpdate();
				System.out.println(uploadername+"_"+refilename+" 테이블 삭제");
			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(con7 != null){JdbcUtilUpload.close(con7);}
				if(pstmt7 != null){JdbcUtilUpload.close(pstmt7);}
			}
			
			
		*/	

		
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/DataSend/datasend_nhis.jsp");
		rd.forward(request, response);
		
		
	}

}
