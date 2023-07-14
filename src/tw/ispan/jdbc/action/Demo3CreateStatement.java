package tw.ispan.jdbc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo3CreateStatement {

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
	
	
	/*public void queryDb1() throws SQLException {
		String sql = "Select * from product";
		Statement state = conn.createStatement();
		ResultSet rs = state.executeQuery(sql);
		System.out.println("Data Result: "+rs.next());
		rs.close();
		state.close();
	}*/
	
	public void queryDB2() throws SQLException {
		String sql = "Select * from product";
		Statement state1 = conn.createStatement();
		ResultSet rs1 = state1.executeQuery(sql);
	
		while(rs1.next()) {
			System.out.println(rs1.getInt(1)+" "+rs1.getString(2)+" "+rs1.getInt(3)+" "+rs1.getDate(4)+" "+rs1.getString(5));
		}
		
		rs1.close();
		state1.close();
	}
	public void findPriceThan(Integer price) throws SQLException {
		String sql = "Select productname,productprice from product where productprice >"+price;
		Statement state1 = conn.createStatement();
		ResultSet rs1 = state1.executeQuery(sql);
		
		
		while(rs1.next()) {
	
			System.out.println(rs1.getString(1)+" "+rs1.getInt(2));
		}
		
		rs1.close();
		state1.close();
	}
	
	public void updateSQL () throws SQLException {
		String sql = "Update product Set productprice = 60 Where productname = 'mask'";
		Statement state = conn.createStatement();
		int row =state.executeUpdate(sql);
		System.out.println("修改比數: "+row);
		
		state.close();
		
	}
	//Hw1
	public void updateSQL1 () throws SQLException {
		String sql = "Update product Set productprice = 15 Where productid = 1001";
		Statement state = conn.createStatement();
		state.executeUpdate(sql);
		System.out.println("Update Product Success");
		state.close();
		
	}
	//HW2
	public void insertDataHW () throws SQLException {
		String sql = "Insert INTO product (productid, productname, productprice, productdate, remark) values(1005,'Mac mini',30000,'2023-03-20','一台')";		
		Statement state = conn.createStatement();
		/*int row = */state.executeUpdate(sql);
		System.out.println("Insert  Product Success");
		state.close();
		
	}
	//HW3
	public void deleteOneDataHW() throws SQLException {
		String sql= "Delete From Product where productname = 'mask'";
		Statement state = conn.createStatement();
		/*int row =*/ state.executeUpdate(sql);
		
		System.out.println("Delete Product Success");
		state.close();
	}
	
	public static void main(String[] args) {
		Demo3CreateStatement demo3 = new Demo3CreateStatement();
		try {
			demo3.createConnection();	
			demo3.queryDB2();
			demo3.updateSQL();
			//demo3.insertDataHW ();
			demo3.findPriceThan(50);;
			//demo3.deleteOneDataHW();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				demo3.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		
	}

}
