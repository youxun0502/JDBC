package tw.ispan.jdbc.action;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo4SQLInjection {

	private Connection conn;
	
	public void createConnection() throws SQLException {
		String connStr = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;TrustServerCertificate=True;";
		String account = "sa";
		String password = "Passw0rd!!";
		
		this.conn = DriverManager.getConnection(connStr,account,password);
		boolean status = !conn.isClosed();
		
		if (status) {
			System.out.println("連線開啟");
		}
	}
	public void closeConnection() throws SQLException {
		if (conn!=null) {
			conn.close();
			System.out.println("關閉連線OK");
			
		}
	}
	
	public boolean checkLogin(String username, String pwd) throws SQLException {
		String sql ="select *from users where username = '"+username+"' and pwd ='"+ pwd +"'";
		System.out.println("sql組裝之後: " + sql);
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		
		boolean checkOK = rs.next();
		
		rs.close();
		state.close();
		
		return checkOK;
	}
	
	public boolean checkLoginPreparedStatement(String username,String pwd) throws SQLException {
		String sql = "select * from users Where username = ? and pwd = ?";
		
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, username);
		preState.setString(2, pwd);
		ResultSet rs = preState.executeQuery();
		
		boolean checkOk =rs.next();
		
		rs.close();
		preState.close();
		
		return checkOk;
	}
	
	
	public static void main(String[] args) {
		Demo4SQLInjection demo4 = new Demo4SQLInjection();
		
		String loginUsername = "'or 3=3--";
		String loginPassword = "7777";
		try {
			demo4.createConnection();
			
//			boolean result = demo4.checkLogin(loginUsername, loginPassword);
			boolean result = demo4.checkLoginPreparedStatement(loginUsername, loginPassword);
			
			if(result) {
				System.out.println("登入成功");
				
			}else {
				System.out.println("帳號密碼錯誤，請重新登入");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				demo4.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
