package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.JdbcUtil;

/**
 * Servlet implementation class datasend_INtoORG
 */
@WebServlet("/J_IN/datasend_INtoORG")
public class Datasend_INtoORG extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Datasend_INtoORG() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nhisTableName = request.getParameter("nhisTableName");
		String statTableName = request.getParameter("statTableName");
		String str_org_send = request.getParameter("org_send");
		String nhisID = request.getParameter("nhisID");
		String statID = request.getParameter("statID");
		int org_send = Integer.parseInt(str_org_send);
		
		
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		Connection con4 = null;
		Connection con5 = null;
		Connection con6 = null;
		Connection con7 = null;
		Connection con8 = null;
		Connection con9 = null;
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		PreparedStatement pstmt7 = null;
		PreparedStatement pstmt8 = null;
		PreparedStatement pstmt9 = null;
		
	
		
		if(org_send == 0){
			
			// org_send 1로 변경
			
			
			try {

				con1 = JdbcUtil.getConnection();

				System.out.println("(1)INDEXER DB connect success");

				String sql = "UPDATE indexer_linknum.indexer_take_data_info set org_send = 1 where nhisTableName = '"
						+ nhisTableName + "' and statTableName = '"+statTableName+"'";

				pstmt1 = con1.prepareStatement(sql);
				pstmt1.executeUpdate();
				
				System.out.println("nhisTableName :"+nhisTableName);
				System.out.println("statTableName :"+statTableName);
				System.out.println("org_send 1로 변경");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con1 != null) {
					JdbcUtil.close(con1);
				}
				if (pstmt1 != null) {
					JdbcUtil.close(pstmt1);
				}
			}
			
			// 대조표 작성 - 테이블 생성
			
			try {

				con2 = JdbcUtil.getConnection();

				System.out.println("(2)INDEXER DB connect success");

				String sql = "CREATE TABLE indexer_checklist."+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+"(linkID int(20) auto_increment, nhisID VARCHAR(20), statID VARCHAR(20), PRIMARY KEY(linkID))";

				pstmt2 = con2.prepareStatement(sql);
				pstmt2.executeUpdate();
				System.out.println("대조표작성 테이블 생성");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con2 != null) {
					JdbcUtil.close(con2);
				}
				if (pstmt2 != null) {
					JdbcUtil.close(pstmt2);
				}
			}
			
			
			
			// 대조표 작성 - 데이터 입력
			
			try {

				con3 = JdbcUtil.getConnection();

				System.out.println("(3)INDEXER DB connect success");

				String sql = "INSERT INTO indexer_checklist."+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+"(nhisID, statID) SELECT indexer_take_nhis."+nhisTableName+".nhisID, indexer_take_stat."+statTableName+".statID FROM indexer_take_nhis."+nhisTableName+" INNER JOIN indexer_take_stat."+statTableName+" ON indexer_take_nhis."+nhisTableName+".personID = indexer_take_stat."+statTableName+".personID";

				pstmt3 = con3.prepareStatement(sql);
				pstmt3.executeUpdate();
				System.out.println("대조표작성 완료");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con3 != null) {
					JdbcUtil.close(con3);
				}
				if (pstmt3 != null) {
					JdbcUtil.close(pstmt3);
				}
			}
			
			//각 기관에 연결번호 저장할 테이블 생성
			
			try {

				con4 = JdbcUtil.getConnection();

				System.out.println("(1)NHIS DB connect success");

				String sql = "CREATE TABLE nhis_take_link."+nhisID+"_"+nhisTableName+"(linkID int(20), nhisID VARCHAR(20), PRIMARY KEY(linkID))";

				pstmt4 = con4.prepareStatement(sql);
				pstmt4.executeUpdate();
				System.out.println("NHIS 연결번호 테이블 생성 완료");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con4 != null) {
					JdbcUtil.close(con4);
				}
				if (pstmt4 != null) {
					JdbcUtil.close(pstmt4);
				}
			}
			
			
			try {

				con5 = JdbcUtil.getConnection();

				System.out.println("(1)STAT DB connect success");

				String sql = "CREATE TABLE stat_take_link."+statID+"_"+statTableName+"(linkID int(20), statID VARCHAR(20), PRIMARY KEY(linkID))";

				pstmt5 = con5.prepareStatement(sql);
				pstmt5.executeUpdate();
				System.out.println("STAT 연결번호 테이블 생성 완료");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con5 != null) {
					JdbcUtil.close(con5);
				}
				if (pstmt5 != null) {
					JdbcUtil.close(pstmt5);
				}
			}
			
			
			
			// 연결번호 기관 전송
			
			//NHIS 부분
			
			try {

				con6 = JdbcUtil.getConnection();

				String sql = "INSERT INTO nhis_take_link."+nhisID+"_"+nhisTableName+"(linkID, nhisID) SELECT linkID, nhisID FROM indexer_checklist."+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName;

				pstmt6 = con6.prepareStatement(sql);
				pstmt6.executeUpdate();
				
				System.out.println("NHIS 연결번호 전송 완료");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con6 != null) {
					JdbcUtil.close(con6);
				}
				if (pstmt6 != null) {
					JdbcUtil.close(pstmt6);
				}
				
			}
			
			try {

				con7 = JdbcUtil.getConnection();

				System.out.println("(2)NHIS DB connect success");

				String sql = "UPDATE nhis_take_data.nhis_take_data_info set receive_indexer = 1 where tableName = " + "'"
						+ nhisTableName + "'";

				pstmt7 = con7.prepareStatement(sql);
				pstmt7.executeUpdate();
				
				System.out.println("NHIS receive_indexer 1로 변경");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con7 != null) {
					JdbcUtil.close(con7);
				}
				if (pstmt7 != null) {
					JdbcUtil.close(pstmt7);
				}
				
			}
			
			
			//STAT 부분
			
			try {

				con8 = JdbcUtil.getConnection();

				String sql = "INSERT INTO stat_take_link."+statID+"_"+statTableName+"(linkID, statID) SELECT linkID, statID FROM indexer_checklist."+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName;

				pstmt8 = con8.prepareStatement(sql);
				pstmt8.executeUpdate();
				
				System.out.println("STAT 연결번호 전송 완료");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con8 != null) {
					JdbcUtil.close(con8);
				}
				if (pstmt8 != null) {
					JdbcUtil.close(pstmt8);
				}
				
			}
			
			try {

				con9 = JdbcUtil.getConnection();

				System.out.println("(2)STAT DB connect success");

				String sql = "UPDATE stat_take_data.stat_take_data_info set receive_indexer = 1 where tableName = " + "'"
						+ statTableName + "'";

				pstmt9 = con9.prepareStatement(sql);
				pstmt9.executeUpdate();
				
				System.out.println("STAT receive_indexer 1로 변경");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (con9 != null) {
					JdbcUtil.close(con9);
				}
				if (pstmt9 != null) {
					JdbcUtil.close(pstmt9);
				}
				
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/J_IN/Datasend_result_INtoORG.jsp");
			rd.forward(request, response);
			
		} //if end
	
		
		
	}

}
