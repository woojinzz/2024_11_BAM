package com.koreaIT.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.koreaIT.BAM.util.Util;
import com.koreaIT.dto.Member;

public class MemberController {
	
	private Scanner sc;
	private List<Member> members;
	private int lastAticleId; // 게시글 아이디
	private int memberCnt; // 멤버아이디
	

	public MemberController(Scanner sc) {
		
		this.sc = sc;
		this.members = new ArrayList<>();
		this.memberCnt = 1;

	}

	public void dojoin() {

		String memberId;
		String memberPw;
		String memberPwChk;
		String memberName;
		System.out.println("======= 회원가입 페이지 =========");

		while (true) {
			System.out.println("아이디  : ");
			memberId = sc.nextLine().trim();

			if (memberId.length() == 0) {
				System.out.println("아이디를 입력해 주세요.");
				continue;
			}

			if (memberIdDupChk(memberId) == false) {
				System.out.printf("%s 는 중복된 아이디 입니다.\n", memberId);
				continue;
			}
			System.out.printf("%s 는 사용 가능한 아이디 입니다.\n", memberId);
			break;
		}

		while (true) {
			System.out.println("비밀번호 : ");
			memberPw = sc.nextLine().trim();

			if (memberPw.length() == 0) {
				System.out.println("비밀번호를 입력해 주세요");
				continue;
			}

			System.out.println("비밀번호 확인 : ");
			memberPwChk = sc.nextLine().trim();

			if (memberPw.equals(memberPwChk) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}

		while (true) {
			System.out.println("이름 : ");
			memberName = sc.nextLine().trim();

			if (memberName.length() == 0) {
				System.out.println("이름을 입력해 주세요");
				continue;
			}
			break;
		}

		Member member = new Member(memberCnt, Util.getDateStr(), memberId, memberPw, memberName);
		members.add(member);

		System.out.printf(memberCnt + "번 %s회원님 가입되었습니다.\n", memberName);
		memberCnt++;
	

	}

	private boolean memberIdDupChk(String memberId) {

		for (Member member : members) {
			if (memberId.equals(member.getMemberId())) {
				return false; // 중복
			}
		}
		return true;// 중복 아님
	}
	
	public void makeMemberTestData() {
		System.out.println("테스트용 회원 데이터 3개 생성");

		for (int i = 1; i <= 3; i++) {
			members.add(new Member(lastAticleId++, Util.getDateStr(), "user" + i, "user" + i, "유저" + i));
		}
	}




}
