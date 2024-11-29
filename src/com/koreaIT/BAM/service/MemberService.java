package com.koreaIT.BAM.service;

import com.koreaIT.BAM.dao.MemberDao;
import com.koreaIT.BAM.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = new MemberDao();
	}

	public boolean loginIdDupChk(String loginId) {

		return memberDao.loginIdDupChk(loginId);
	}

	public int joinMember(String loginId, String loginPw, String loginName) {
		return memberDao.joinMember(loginId, loginPw, loginName);

	}

	public Member getMemberByLoginId(String memberLoginId) {
		return memberDao.getMemberByLoginId(memberLoginId);
	}

}
