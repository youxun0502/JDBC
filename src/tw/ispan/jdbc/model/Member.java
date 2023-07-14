package tw.ispan.jdbc.model;

public class Member {

	public Member() {
	}

	private int memberId;
	private String memberName;
	private String memberAddress;
	private String phone;

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [memberId=");
		builder.append(memberId);
		builder.append(", memberName=");
		builder.append(memberName);
		builder.append(", memberAddress=");
		builder.append(memberAddress);
		builder.append(", phone=");
		builder.append(phone);
		builder.append("]");
		return builder.toString();
	}


	
}
