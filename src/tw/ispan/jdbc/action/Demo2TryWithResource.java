package tw.ispan.jdbc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo2TryWithResource {

	public static void main(String[] args) {
		String connStr = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB;TrustServerCertificate=True;";
		String connStr2 = connStr + "user = sa;password = Passw0rd!!";
		// String account = "sa";
		// String password = "Passw0rd!!";

		try (Connection conn = DriverManager.getConnection(connStr2)

		) {
			boolean status = !conn.isClosed();
			System.out.println("status: " + status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
