package tw.ispan.jdbc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.ispan.jdbc.util.GetDataUtil;

public class MemberDao {

	private Connection conn;

	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	// 新增資料
	public void addMember(Member m) throws SQLException {
		String sql = "insert into member(memberId, memberName, [address], phone) values(?,?,?,?)";
		PreparedStatement preState = conn.prepareStatement(sql);

		preState.setInt(1, m.getMemberId());
		preState.setString(2, m.getMemberName());
		preState.setString(3, m.getMemberAddress());
		preState.setString(4, m.getPhone());

		int row = preState.executeUpdate();
		System.out.println("新增 " + row + " 筆資料");

		preState.close();

	}
	// 查詢資料1

	public Member findMemberByID(Integer id) throws SQLException {
		String sql = "select * from member where memberid =?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);

		Member returnMember = new Member();
		ResultSet rs = preState.executeQuery();
		if (rs.next()) {

			returnMember.setMemberId(rs.getInt("memberId"));
			returnMember.setMemberName(rs.getString("memberName"));
			returnMember.setMemberAddress(rs.getString("address"));
			returnMember.setPhone(rs.getString("phone"));
			rs.close();
			preState.close();
			return returnMember;
		}
		rs.close();
		preState.close();

		return null;

	}

	// 查詢資料2

	public List<Member> findAllMember() throws SQLException {
		String sql = "Select * from member";
		PreparedStatement preState = conn.prepareStatement(sql);
		ResultSet rs = preState.executeQuery();

		List<Member> LM = new ArrayList<>();

		while (rs.next()) {
			Member returnMember = new Member();
			returnMember.setMemberId(rs.getInt("memberId"));
			returnMember.setMemberName(rs.getString("memberName"));
			returnMember.setMemberAddress(rs.getString("address"));
			returnMember.setPhone(rs.getString("phone"));

			LM.add(returnMember);
		}
		rs.close();
		preState.close();
		return LM;
	}

	// 修改資料1

	public void updatePhoneById(String newphone, Integer userId) throws SQLException {
	
		
		
		String sql = "Update member set phone = ? where memberId = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(2, userId);
		preState.setString(1, newphone);
		preState.executeUpdate();
		System.out.println("修改OK!!");

		preState.close();

	}

	// 修改資料2
	public void updatePhoneById(Member member) throws SQLException {
		String sql = "Update member set phone = ? where memberId = ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setString(1, member.getPhone());
		preState.setInt(2, member.getMemberId());

		preState.executeUpdate();
		System.out.println("修改OK!!");

		preState.close();

	}

	// 刪除資料
	public void deleteMemberById(Integer id) throws SQLException {
		String sql = "delete from member where memberId= ?";
		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);
		preState.executeUpdate();
		System.out.println("刪除id為" + id + "的資料OK!!");
		preState.close();
	}

	public void InsertAQI(int leftString,String rightString) throws SQLException {
		
		String sql = "insert into AQI values(?,?)";
		//GetDataUtil actionUtil = new GetDataUtil();
		//List<String> AQI = actionUtil.getURLContent();

		/*String[] string1;
		int leftString ;
		String rightString = null;*/

		PreparedStatement preState = conn.prepareStatement(sql);
	/*	for (String string : AQI) {
			string1 = string.split(",");
			leftString = Integer.parseInt(string1[0]) ;
			
			rightString = string1[1];*/
			preState.setInt(1, leftString);
			preState.setString(2, rightString);

			preState.executeUpdate();

		
		preState.close();

	}
}
