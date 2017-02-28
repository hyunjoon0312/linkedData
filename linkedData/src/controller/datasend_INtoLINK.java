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
 * Servlet implementation class datasend_IntoLINK
 */
@WebServlet("/J_IN/datasend_IntoLINK")
public class datasend_INtoLINK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public datasend_INtoLINK() {
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
	
		
		String str_checklist_send = request.getParameter("checklist_send");
		int checklist_send = Integer.parseInt(str_checklist_send);
		String nhisTableName = request.getParameter("nhisTableName");
		String statTableName = request.getParameter("statTableName");
		String nhisID = request.getParameter("nhisID");
		String statID = request.getParameter("statID");
		String inID = request.getParameter("inID");
		String inName = request.getParameter("inName");
		
		
		if(checklist_send == 0){
			
			Connection con1 = null;
			Connection con2 = null;
			Connection con3 = null;
			Connection con4 = null;

			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			PreparedStatement pstmt3 = null;
			PreparedStatement pstmt4 = null;

			
			//indexer_take_data_info 테이블에서 checklist_send 1로 변경
			
			try {

				con1 = JdbcUtil.getConnection();

				System.out.println("(1)INDEXER_linknum DB connect success");

				String sql = "UPDATE indexer_linknum.indexer_take_data_info set checklist_send = 1 where nhisTableName = '"
						+ nhisTableName + "' and statTableName = '"+statTableName+"'";

				pstmt1 = con1.prepareStatement(sql);
				pstmt1.executeUpdate();
				
				System.out.println("checklist_send 1로 변경");

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
			
			
			// indexer_checklist 대조표 저장할 테이블 생성
			
			try {

				con2 = JdbcUtil.getConnection();

				System.out.println("(1)LINK DB connect success");

				String sql = "CREATE TABLE link_take_checklist."+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+"(linkID int(20), nhisID VARCHAR(20), statID VARCHAR(20), PRIMARY KEY(linkID))";

				pstmt2 = con2.prepareStatement(sql);
				pstmt2.executeUpdate();
				System.out.println("LINK 대조표 저장 테이블 생성 완료");

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
			
			//대조표 저장
			
			try {

				con3 = JdbcUtil.getConnection();

				String sql = "INSERT INTO "+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+"(linkID, nhisID, statID) SELECT * FROM indexer_checklist."+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName;

				pstmt3 = con3.prepareStatement(sql);
				pstmt3.executeUpdate();
				
				System.out.println("대조표 전송 완료");

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
			
			
			// 대조표 정보 저장 - 해당테이블 : link_take_checklist_info
			
			try {

				con4 = JdbcUtil.getConnection();

				String sql = "INSERT INTO link_take_checklist.link_take_checklist_info(indexerID, indexerName, tableName) VALUES(?,?,?)";

				pstmt4 = con4.prepareStatement(sql);
				
				pstmt4.setString(1, inID);
				pstmt4.setString(2, inName);
				pstmt4.setString(3, nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName);
				
				pstmt4.executeUpdate();
				
				System.out.println("대조표 정보 전송 완료");

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
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/J_IN/Datasend_result_INtoLINK.jsp");
			rd.forward(request, response);
			
			
		} // if end
		
		
	}

}
