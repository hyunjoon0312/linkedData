package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class DatasendServlet
 */
@WebServlet("/datasend_nhis")
public class DatasendServlet_NHIS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatasendServlet_NHIS() {
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
		HttpSession session = request.getSession();
		session.setAttribute("filename", filename);
		
		//filename을 기준으로 db에서 데이터 꺼내와서 stat에게 넣어주기.
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://112.72.158.187:3306/uploadFile", "hyunjoon",
					"hyunjoon");
			System.out.println("UploadDB connect success");
		
		
			String sql = "UPDATE uploadFile.UploadFileInfo set nhis_send = 1 where filename = "+filename;
		
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("datasend_nhis.jsp");
		rd.forward(request, response);
		
		
	}

}
