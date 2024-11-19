package com.koreaIT.dto;

public class Member {

	private int memberId;
	private String memberRegDate;
	private String memberLoginId;
	private String memberLoginPw;
	private String memberLoginName;

	public Member(int memberId, String memberRegDate, String memberLoginId, String memberLoginPw, String memberLoginName) {

		this.memberId = memberId;
		this.memberRegDate = memberRegDate;
		this.memberLoginId = memberLoginId;
		this.memberLoginPw = memberLoginPw;
		this.memberLoginName = memberLoginName;

	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberRegDate() {
		return memberRegDate;
	}

	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}

	public String getMemberLoginId() {
		return memberLoginId;
	}

	public void setMemberLoginId(String memberLoginId) {
		this.memberLoginId = memberLoginId;
	}

	public String getMemberLoginPw() {
		return memberLoginPw;
	}

	public void setMemberPw(String memberLoginPw) {
		this.memberLoginPw = memberLoginPw;
	}

	public String getMemberLoginName() {
		return memberLoginName;
	}

	public void setMemberLoginName(String memberLoginName) {
		this.memberLoginName = memberLoginName;
	}

//	public void increaseViewCnt() {
//		this.views++;
//	}

}
