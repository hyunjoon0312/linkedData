package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginServiceNEOK;
import vo.MemberNEOK;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginNEOK")
public class LoginServletNEOK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServletNEOK() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest NEOKrequest, HttpServletResponse NEOKresponse) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String NEOKid = NEOKrequest.getParameter("NEOKid");
		String NEOKpasswd = NEOKrequest.getParameter("NEOKpasswd");
		LoginServiceNEOK loginServiceNEOK = new LoginServiceNEOK();
		MemberNEOK loginMemberNEOK = loginServiceNEOK.getLoginMember(NEOKid,NEOKpasswd);
		//�α����� �����Ǹ� Member ��ü�� �Ѿ���� �����ϸ� null�� �Ѿ��
		
		if(loginMemberNEOK != null){
			HttpSession session = NEOKrequest.getSession();
			session.setAttribute("NEOKid", NEOKid);
			NEOKresponse.sendRedirect("AfterLoginNEOK.html");
			System.out.println("�α��μ���");
		}else{
			NEOKresponse.setCharacterEncoding("UTF-8");
			
			NEOKresponse.setContentType("text/html; charset=UTF-8");
			PrintWriter out = NEOKresponse.getWriter();
			out.println("<script>");
			out.println("alert('�α��� ����');");
			out.println("location.replace('LoginFormNEOK.jsp')");
			out.println("</script>");
			out.close();
			System.out.println("�α��ν���");
			
		}
	}

}
