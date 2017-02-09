package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//�����ͺ��̽� �۾��� �� �� �ݺ������� �����ؾ� �ϴ� �۾��� �����ϴ� Ŭ����
public class JdbcUtilLogin {
	
	public static Connection getLoginConnection(){
		
		Connection conLogin =null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conLogin = DriverManager.getConnection("jdbc:mysql://210.115.187.74:3306/hyunjoon_study", "hyunjoon",
					"hyunjoon");
			System.out.println("connect success");
		}catch(Exception e){
			e.printStackTrace();
		}
		return conLogin;
		
		
	}

	
	public static void close(Connection conLogin){
		try {
			conLogin.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt){
		try {
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try {
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conLogin){
		try {
			conLogin.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conLogin){
		try {
			conLogin.rollback();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}











