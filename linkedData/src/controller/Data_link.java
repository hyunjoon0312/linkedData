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
		
		request.setCharacterEncoding("utf-8");
		
		System.err.println("Data_link.java");
		
		String nhisID = request.getParameter("nhisID");
		String nhisTableName = request.getParameter("nhisTableName");
		String statID = request.getParameter("statID");
		String statTableName = request.getParameter("statTableName");
		
		
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

			String sql = "CREATE TABLE link_safe_DB."+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+"(COUNT int(20) auto_increment, STND_Y int(20), YKIHO_ID INT(20), YKIHO_GUBUN_CD INT(20), ORG_TYPE INT(20), YKIHO_SIDO INT(20), SICKBED_CNT INT(20), DR_CNT INT(20), CT_YN INT(20), MRI_YN INT(20), PET_YN INT(20), REPORT_YMD VARCHAR(20), ADDRESS INT(20), GENDER INT(20), DEATH_YMD VARCHAR(20), DEATH_TIME INT(20), DEATH_PLACE INT(20), DEATH_JOB VARCHAR(20), MARRY INT(20), EDU INT(20), DEATH_CAU1 VARCHAR(20), DEATH_CAU1_Parent VARCHAR(20), DEATH_AGE INT(20), PRIMARY KEY(COUNT))";

			pstmt1 = con1.prepareStatement(sql);
			pstmt1.executeUpdate();
			System.out.println("연계 데이터 저장 테이블 생성 완료");

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
		
		
		
		//데이터 JOIN 후 Input
		
		try {

			con2 = JdbcUtil.getConnection();

			String sql = "INSERT INTO link_safe_DB."+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+"(STND_Y , YKIHO_ID , YKIHO_GUBUN_CD , ORG_TYPE, YKIHO_SIDO, SICKBED_CNT, DR_CNT, CT_YN, MRI_YN, PET_YN, REPORT_YMD, ADDRESS, GENDER, DEATH_YMD, DEATH_TIME, DEATH_PLACE, DEATH_JOB, MARRY, EDU, DEATH_CAU1, DEATH_CAU1_Parent, DEATH_AGE) SELECT link_take_nhis."+nhisID+"_"+nhisTableName+".STND_Y,"+" link_take_nhis."+nhisID+"_"+nhisTableName+".YKIHO_ID,"+" link_take_nhis."+nhisID+"_"+nhisTableName+".YKIHO_GUBUN_CD, "+"link_take_nhis."+nhisID+"_"+nhisTableName+".ORG_TYPE, "+"link_take_nhis."+nhisID+"_"+nhisTableName+".YKIHO_SIDO, "+"link_take_nhis."+nhisID+"_"+nhisTableName+".SICKBED_CNT, "+"link_take_nhis."+nhisID+"_"+nhisTableName+".DR_CNT, "+"link_take_nhis."+nhisID+"_"+nhisTableName+".CT_YN, "+"link_take_nhis."+nhisID+"_"+nhisTableName+".MRI_YN, "+"link_take_nhis."+nhisID+"_"+nhisTableName+".PET_YN, link_take_stat."+statID+"_"+statTableName+".REPORT_YMD, "+"link_take_stat."+statID+"_"+statTableName+".ADDRESS, "+"link_take_stat."+statID+"_"+statTableName+".GENDER, "+"link_take_stat."+statID+"_"+statTableName+".DEATH_YMD, "+"link_take_stat."+statID+"_"+statTableName+".DEATH_TIME, "+"link_take_stat."+statID+"_"+statTableName+".DEATH_PLACE, "+"link_take_stat."+statID+"_"+statTableName+".DEATH_JOB, "+"link_take_stat."+statID+"_"+statTableName+".MARRY, "+"link_take_stat."+statID+"_"+statTableName+".EDU, "+"link_take_stat."+statID+"_"+statTableName+".DEATH_CAU1, "+"link_take_stat."+statID+"_"+statTableName+".DEATH_CAU1_Parent, "+"link_take_stat."+statID+"_"+statTableName+".DEATH_AGE"+" FROM link_take_nhis."+nhisID+"_"+nhisTableName+" INNER JOIN link_take_stat."+statID+"_"+statTableName+" ON link_take_nhis."+nhisID+"_"+nhisTableName+".linkID = link_take_stat."+statID+"_"+statTableName+".linkID";

			pstmt2 = con2.prepareStatement(sql);
			pstmt2.executeUpdate();
			
			System.out.println("데이터 연계 완료");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("err:" + e.toString());
		} finally {
			if (con2 != null) {
				JdbcUtil.close(con2);
			}
			if (pstmt2 != null) {
				JdbcUtil.close(pstmt2);
			}
			
		}
		
		
		
		// 데이터 연계해줬으니까 link_take_checklist.link_take_checklist_info 에서 data_link 1로 변경 해줌.
		
		try {

			con3 = JdbcUtil.getConnection();

			System.out.println("(2)LINK DB connect success");

			String sql = "UPDATE link_take_checklist.link_take_checklist_info set data_link = 1 where checklist_tableName = '" +nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+ "'";

			pstmt3 = con3.prepareStatement(sql);
			pstmt3.executeUpdate();
			
			System.out.println("data_link 1로 변경");

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
		
		
		// uploadFile.UploadgFileInfo 에도 데이터 연계 했다고 알려줌.
		
		try {

			con3 = JdbcUtil.getConnection();

			System.out.println("(1)uploadFile DB connect success");

			String sql = "UPDATE uploadFile.UploadFileInfo set data_link = 1 where id_filename= '"+nhisTableName+ ".csv'";

			pstmt3 = con3.prepareStatement(sql);
			pstmt3.executeUpdate();
			
			System.out.println("data_link 1로 변경");

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
		
		
		// uploadFile.UploadgFileInfo 에도 연계 데이터 테이블 이름 저장
		
				try {
					
					
					
					con3 = JdbcUtil.getConnection();

					System.out.println("(2)uploadFile DB connect success");

					String sql = "UPDATE uploadFile.UploadFileInfo set link_data_tablename = '"+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+"' where id_filename= '"+nhisTableName+ ".csv'";

					System.err.println("테이블 이름 : "+nhisTableName);
					
					pstmt3 = con3.prepareStatement(sql);
					pstmt3.executeUpdate();
					
					System.out.println("연계 데이터 테이블 이름 : "+nhisID+"_"+nhisTableName+"_"+statID+"_"+statTableName+" where id_filename= '"+nhisTableName);

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
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/J_LINK/data_link_result.jsp");
		rd.forward(request, response);
		
		
		
	}

}
