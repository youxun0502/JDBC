package tw.ispan.jdbc.action;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import tw.ispan.jdbc.model.MemberDao;
import tw.ispan.jdbc.util.ConnectionFactory;
import tw.ispan.jdbc.util.GetDataUtil;

public class Demo12GetDataAction {

	public static void main(String[] args) {

		Connection conn = ConnectionFactory.createSQLServerConnection();
		MemberDao mDao = new MemberDao(conn);

		GetDataUtil actionUtil = new GetDataUtil();
		List<String> AQI = actionUtil.getURLContent();

		String[] string1;
		int leftString;
		String rightString = null;

		try {
			for (String string : AQI) {
				string1 = string.split(",");
				leftString = Integer.parseInt(string1[0]);
				rightString = string1[1];
				mDao.InsertAQI(leftString,rightString);
			}
			System.out.println("資料已匯入資料庫");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
