package svc;

import static db.JdbcUtilLogin.*;

import java.sql.Connection;

import dao.LoginDAONEOK;
import vo.MemberNEOK;
public class LoginServiceNEOK {

	public MemberNEOK getLoginMember(String NEOKid, String NEOKpasswd) {
		// TODO Auto-generated method stub
		LoginDAONEOK loginDAONEOK = LoginDAONEOK.getInstance();
		Connection NEOKcon = getLoginConnection();
		loginDAONEOK.setConnection(NEOKcon);
		MemberNEOK loginMemberNEOK = loginDAONEOK.selectLoginMemberNEOK(NEOKid,NEOKpasswd);
		close(NEOKcon);
		return loginMemberNEOK;
	}
	
}








