package com.koreaIT.dto;

public class Member {

	private int memberCnt;
	private String memberRegDate;
	private String memberId;
	private String memberPw;
	private String memberName;

	public Member(int memberCnt, String memberRegDate, String memberId, String memberPw, String memberName) {

		this.memberCnt = memberCnt;
		this.memberRegDate = memberRegDate;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;

	}

	public int getMemberCnt() {
		return memberCnt;
	}

	public void setMemberCnt(int memberCnt) {
		this.memberCnt = memberCnt;
	}

	public String getMemberRegDate() {
		return memberRegDate;
	}

	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

//	public void increaseViewCnt() {
//		this.views++;
//	}

}
