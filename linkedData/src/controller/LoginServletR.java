package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginServiceR;
import vo.MemberR;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginR")
public class LoginServletR extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServletR() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Rid = request.getParameter("Rid");
		String Rpasswd = request.getParameter("Rpasswd");
		LoginServiceR loginServiceR = new LoginServiceR();
		MemberR loginMemberR = loginServiceR.getLoginMember(Rid,Rpasswd);
		//�α����� �����Ǹ� Member ��ü�� �Ѿ���� �����ϸ� null�� �Ѿ��
		
		if(loginMemberR != null){
			HttpSession session = request.getSession();
			session.setAttribute("Rid", Rid);
			response.sendRedirect("AfterLoginR.html");
			System.out.println("�α��μ���");
		}else{
			response.setCharacterEncoding("UTF-8");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('�α��� ����');");
			out.println("location.replace('LoginFormR.jsp')");
			out.println("</script>");
			out.close();
			System.out.println("�α��ν���");
			
		}
	}

}