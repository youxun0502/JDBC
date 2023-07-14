package tw.ispan.jdbc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.UUID;

public class Demo9BetchUpdate {
	
	private Connection conn;

	public void createConnection() throws SQLException {
		String connStr = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;TrustServerCertificate=True;";
		String account = "sa";
		String password = "Passw0rd!!";

		this.conn = DriverManager.getConnection(connStr, account, password);
		boolean status = !conn.isClosed();

		if (status) {
			System.out.println("連線開啟");
		}
	}

	public void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
			System.out.println("關閉連線OK");

		}
	}
	
	
	public  void couponSending() throws SQLException {
		LinkedList<String> emails = new LinkedList<>();
		emails.add("Jerry2@gmail");
		emails.add("Mary2@gmail");
		emails.add("amy2@gmail");
		emails.add("Abby2@gmail");
		
		String sql = "Insert into couponCode(user_email, code) values(?,?)";
		PreparedStatement prestate = conn.prepareStatement(sql);
		
		//裝到同一批
		for (String e : emails) {
			prestate.setString(1,e);
			prestate.setString(2, UUID.randomUUID().toString());
			prestate.addBatch();//隱含的List
		}
		
		prestate.executeBatch();
		System.out.println("批次處理完成");
		prestate.close();
	}
	
	public static void main(String[] args) {
	Demo9BetchUpdate demo9 = new Demo9BetchUpdate();
	try {
		demo9.createConnection();
		demo9.couponSending();
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			demo9.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	}

}
