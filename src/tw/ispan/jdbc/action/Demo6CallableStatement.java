package tw.ispan.jdbc.action;

import java.io.BufferedOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Demo6CallableStatement {

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

	public void callStoredProcedure() throws SQLException {
		CallableStatement callState = conn.prepareCall("{call productProc(?,?)}");
		callState.setInt(1, 1004);
		callState.registerOutParameter(2, java.sql.Types.VARCHAR);

		callState.execute();
		String name = callState.getString(2);
		System.out.println("name:" + name);
		callState.close();

	}

	public static void main(String[] args) {
		Demo6CallableStatement demo6 = new Demo6CallableStatement();

		try {
			demo6.createConnection();
			demo6.callStoredProcedure();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				demo6.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}