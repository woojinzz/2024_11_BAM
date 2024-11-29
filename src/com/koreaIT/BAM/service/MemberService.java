package com.koreaIT.BAM.service;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dao.MemberDao;
import com.koreaIT.BAM.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public boolean loginIdDupChk(String loginId) {

		return memberDao.loginIdDupChk(loginId);
	}

	public void joinMember(String loginId, String loginPw, String loginName) {
		memberDao.joinMember(loginId, loginPw, loginName);

	}

	public Member getMemberByLoginId(String memberLoginId) {
		return memberDao.getMemberByLoginId(memberLoginId);
	}

}
