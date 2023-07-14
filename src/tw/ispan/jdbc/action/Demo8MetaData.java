package tw.ispan.jdbc.action;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo8MetaData {

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
	
	public void getDatabaseMetaData() throws SQLException {

		DatabaseMetaData metaData = conn.getMetaData();
		
		System.out.println("ProductName: "+metaData.getDatabaseProductName());
		System.out.println("DatabaseProductVersion: "+metaData.getDatabaseProductVersion());
		System.out.println("DriverName: "+metaData.getDriverName());
		System.out.println("DriverVersion: "+metaData.getDriverVersion());
		System.out.println("URL: "+metaData.getURL());
		System.out.println("Username: "+metaData.getUserName()+"%n%n");
	}
	
	public void getResultSetMetaData() throws SQLException {
		String sql = "select productid P_id ,productName p_name from product";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		ResultSetMetaData metaData = rs.getMetaData();
		System.out.println("ColumnCount():"+metaData.getColumnCount());
		System.out.println("ColumnName(1):"+metaData.getColumnName(1));
		System.out.println("ColumnName(2):"+metaData.getColumnName(2));
		System.out.println("ColumnLabel(1):"+metaData.getColumnLabel(1));
		System.out.println("ColumnLabel(2):"+metaData.getColumnLabel(2));
		System.out.println("ColumnTypeName(1):"+metaData.getColumnTypeName(1));
		System.out.println("ColumnTypeName(2):"+metaData.getColumnTypeName(2));
		System.out.println("ColumnDisplaySize(1):"+metaData.getColumnDisplaySize(1));
		System.out.println("ColumnDisplaySize(2):"+metaData.getColumnDisplaySize(2));

		rs.close();
		state.close();
		
	}
	
//	欄位名稱+資料
	public void testResultSetMetaDataPlusData() throws SQLException {
		String sql = "select * from product";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		ResultSetMetaData metaData = rs.getMetaData();
		
		System.out.println(metaData.getColumnName(1)+" "+metaData.getColumnName(2)+" "+metaData.getColumnName(3)+" "+metaData.getColumnName(4)+" "+metaData.getColumnName(5));
		while (rs.next()) {
			System.out.println(rs.getInt(1)+"      "+rs.getString(2)+"        "+rs.getString(3)+"        "+rs.getString(4)+"        "+rs.getString(5));
		}
	
		rs.close();
		state.close();
		
	}
	
	public static void main(String[] args) {
	
		Demo8MetaData demo8 = new Demo8MetaData();
		
		 try {
			demo8.createConnection();
			//demo8.getResultSetMetaData();
			//demo8.getDatabaseMetaData();
			demo8.testResultSetMetaDataPlusData();
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		}finally {
			try {
				demo8.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
