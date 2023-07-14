package tw.ispan.jdbc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo1TestConnection {

	public static void main(String[] args) {
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String connStr = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;TrustServerCertificate=True;";
			String connStr2 = connStr + "user = sa;password = Passw0rd!!";
		//	String account = "sa";
			//String password = "Passw0rd!!";

			// Connection conn = DriverManager.getConnection(connStr, account, password);

			Connection conn = DriverManager.getConnection(connStr2);

			boolean status = !conn.isClosed();
			System.out.println("status:" + status);
			System.out.println("連線OK");

			conn.close();

			System.out.println("有找到");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
