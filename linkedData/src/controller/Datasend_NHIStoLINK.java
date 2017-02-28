package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.JdbcUtil;

/**
 * Servlet implementation class Datasend_NHIStoLINK
 */
@WebServlet("/J_NHIS/datasend_NHIStoLINK")
public class Datasend_NHIStoLINK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Datasend_NHIStoLINK() {
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

		
		String nhisID = request.getParameter("NHISid");
		String tableName = request.getParameter("tableName"); 
		
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		
		// 연계 기관에서 nhis data 받을 테이블 생성
		
		// 테이블 생성해야함.
		
		try {

			con1 = JdbcUtil.getConnection();

			System.out.println("(1)LINK DB connect success");

			String sql = "CREATE TABLE link_take_nhis."+nhisID+"_"+tableName+"(linkID int(20), , PRIMARY KEY(linkID))";

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
		
	}

}
