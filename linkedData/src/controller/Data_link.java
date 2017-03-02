package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import db.JdbcUtil;

/**
 * Servlet implementation class Data_link
 */
@WebServlet("/J_LINK/data_link")
public class Data_link extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Data_link() {
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
		
		
		String nhisID = request.getParameter("nhisID");
		String nhisTableName = request.getParameter("nhisTableName");
		String statID = request.getParameter("statID");
		String statTableName = request.getParameter("statTablename");
		
		
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		
		
		
		// link_safe_DB 에 테이블 생성
		// 조인할 테이블 생성하고 조인해서 넣으면 끝. 
		
		try {

			con1 = JdbcUtil.getConnection();

			System.out.println("(1)LINK DB connect success");

			String sql = "CREATE TABLE link_safe_DB."+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+"(linkID int(20), nhisID VARCHAR(20), statID VARCHAR(20), PRIMARY KEY(linkID))";

			pstmt1 = con1.prepareStatement(sql);
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
