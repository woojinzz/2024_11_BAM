package com.koreaIT.BAM.dao;

import java.util.List;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.util.Util;

public class MemberDao {

	private int lastId;
	private List<Member> members;

	public MemberDao() {
		this.lastId = 1;
		this.members = Container.members;
	}

	// 로그인 아이디 체크
	public Member getMemberByLoginId(String loginId) {
		Member foundMember = null;

		for (Member member : members) {
			if (member.getLoginId().equals(loginId)) {
				foundMember = member;
				return foundMember;// 아이디 존재
			}
		}
		return null;// 아이디 존재안함
	}

	public boolean loginIdDupChk(String loginId) {
		Member member = getMemberByLoginId(loginId);

		if (member != null) {
			return false;
		}
		return true;// 중복 아님
	}

	public int joinMember(String loginId, String loginPw, String loginName) {
		members.add(new Member(lastId, Util.getDateStr(), loginId, loginPw, loginName));
		return lastId++;
	}

}
