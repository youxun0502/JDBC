package tw.ispan.jdbc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Demo5PreparedStatement {
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
	
	public void createTableDDL() throws SQLException {
		String sql = "create table profiles(id int primary key identity(1,1),[name] nvarchar(50) not null,[address] nvarchar(100),phone varchar(20))";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.execute();
		
		preState.close();
		System.out.println("create table Success");
	}
	
	public void insertData(String name,String address,String phone) throws SQLException {
		String sql = "insert into profiles (name,address,phone) values (?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1,name);
		preState.setString(2,address);
		preState.setString(3,phone);

		int row = preState.executeUpdate();
		System.out.println("新增了"+row+"筆資料");
	}
	
	//用preparedStatement >用address(?)找出資料
	
	public void queryByAddress(String address) throws SQLException {
	String sql ="Select * from profiles where address = ?";
	PreparedStatement preState = conn.prepareStatement(sql);
	preState.setString(1,address);
	ResultSet rs = preState.executeQuery();
	
	while(rs.next()) {
		System.out.println(rs.getInt("id")+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
	}//如果用別名就要用別名的名稱
	
	rs.close();
	preState.close();
	}
	
	public void updatePhoneByName(String name, String newPhone) throws SQLException {
		String sql = "update profiles set phone = ? where name = ?";
		PreparedStatement prestate = conn.prepareStatement(sql);
		prestate.setString(1, newPhone);
		prestate.setString(2, name);
		
		int row = prestate.executeUpdate();
		
		System.out.println("update "+row+" data");
		prestate.close();
	}

	public static void main(String[] args) {
		Demo5PreparedStatement demo5 = new Demo5PreparedStatement();
		try {
			demo5.createConnection();
			//demo5.createTableDDL();
			//demo5.insertData("館長", "林口", "3345678");
			demo5.queryByAddress("林口");
			demo5.updatePhoneByName("館長", "777777");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				demo5.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
