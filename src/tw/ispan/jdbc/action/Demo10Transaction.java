package tw.ispan.jdbc.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tw.ispan.jdbc.util.ConnectionFactory;

public class Demo10Transaction {
	private Connection conn;

	public Demo10Transaction(Connection conn) {
		this.conn = conn;
	}

	public void controlTransaction() {
		String sql = "update product set remark = ? where productid = ?";

		try {
			conn.setAutoCommit(false);// 關閉自動認可模式 ->隱含交易模式
			PreparedStatement prestate = conn.prepareStatement(sql);
			prestate.setString(1, "因疫情關係，不在24H保障內");
			prestate.setInt(2, 1003);
			prestate.execute();

			prestate.setString(1,
					"因疫情關係，不在24H保障內");
			prestate.setInt(2, 1004);
			prestate.execute();

			conn.commit();
			System.out.println("Commit OK!!!");
			conn.setAutoCommit(true);

			prestate.close();
		} catch (SQLException e) {
			try {
				System.out.println("Something wrong and rollback!!!");
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Connection conn = ConnectionFactory.createSQLServerConnection();

		Demo10Transaction demo10 = new Demo10Transaction(conn);//因為其他方法裡有用到demo10物件的conn所以需要將上個conn設定給demo10的conn
		

		demo10.controlTransaction();

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
