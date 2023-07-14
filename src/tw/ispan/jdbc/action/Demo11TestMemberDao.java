package tw.ispan.jdbc.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.ispan.jdbc.model.Member;
import tw.ispan.jdbc.model.MemberDao;
import tw.ispan.jdbc.util.ConnectionFactory;

public class Demo11TestMemberDao {

	public static void main(String[] args) {

		Connection conn = ConnectionFactory.createSQLServerConnection();
		MemberDao mDao = new MemberDao(conn);

//		Member m1 = new Member();
//		m1.setMemberId(1001);
//		m1.setMemberName("Jerry");
//		m1.setMemberAddress("中壢");
//		m1.setPhone("3345678");

//		Member m2 = new Member();
//		m2.setMemberId(1002);
//		m2.setMemberName("Micky");
//		m2.setMemberAddress("桃園");
//		m2.setPhone("3345679");

		try {
			// mDao.addMember(m1);
			/*
			 * Member returnMember = mDao.findMemberByID(1002);
			 * 
			 * if (returnMember != null) { System.out.println(returnMember); } else {
			 * System.out.println("沒有這筆資料"); }
			 */
			//mDao.addMember(m2);

			//test findALL()
			/*List<Member> LM = mDao.findAllMember();
			
			for (Member member : LM) {
				System.out.println(member);
			}*/
			
			/*mDao.updatePhoneById("6666666",1001);*/
			
			/*Member returnMember = mDao.findMemberByID(1002);
			returnMember.setPhone("7777777");//只改了member物件的值，沒有修改到資料庫的值
			mDao.updatePhoneById(returnMember);*/
			
			
			mDao.deleteMemberById(1002);
			
		} catch (SQLException e) {
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
