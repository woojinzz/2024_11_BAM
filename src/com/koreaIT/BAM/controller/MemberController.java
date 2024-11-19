package com.koreaIT.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.koreaIT.BAM.util.Util;
import com.koreaIT.dto.Member;

public class MemberController extends Controller {

	private int memberId; // 멤버아이디
	private static List<Member> members;

	public MemberController(Scanner sc) {

		this.sc = sc;
		members = new ArrayList<>();
		this.memberId = 1;
		loginedMember = null;
	}

	@Override
	public void doAction(String cmd, String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
		}
	}

	public void doJoin() {
		
		if (isLogined()) {
			System.out.println("로그인 상태입니다.");
			return;
		}

		String memberLoginId;
		String memberLoginPw;
		String memberLoginPwChk;
		String memberLoginName;
		System.out.println("======= 회원가입 페이지 =========");

		while (true) {
			System.out.println("아이디  : ");
			memberLoginId = sc.nextLine().trim();

			if (memberLoginId.length() == 0) {
				System.out.println("아이디를 입력해 주세요.");
				continue;
			}

			if (memberIdDupChk(memberLoginId) == false) {
				System.out.printf("%s 는 중복된 아이디 입니다.\n", memberLoginId);
				continue;
			}
			System.out.printf("%s 는 사용 가능한 아이디 입니다.\n", memberLoginId);
			break;
		}

		while (true) {
			System.out.println("비밀번호 : ");
			memberLoginPw = sc.nextLine().trim();

			if (memberLoginPw.length() == 0) {
				System.out.println("비밀번호를 입력해 주세요");
				continue;
			}

			System.out.println("비밀번호 확인 : ");
			memberLoginPwChk = sc.nextLine().trim();

			if (memberLoginPw.equals(memberLoginPw) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}

		while (true) {
			System.out.println("이름 : ");
			memberLoginName = sc.nextLine().trim();

			if (memberLoginName.length() == 0) {
				System.out.println("이름을 입력해 주세요");
				continue;
			}
			break;
		}

		Member member = new Member(memberId, Util.getDateStr(), memberLoginId, memberLoginPw, memberLoginName);
		members.add(member);

		System.out.printf(memberId + "번 %s회원님 가입되었습니다.\n", memberLoginName);
		memberId++;

	}

	private void doLogin() {
		
		if (isLogined()) {
			System.out.println("로그인 상태입니다.");
			return;
		}

		String memberLoginId;
		String memberLoginPw;

		System.out.println("아이디  : ");
		memberLoginId = sc.nextLine().trim();
		System.out.println("비밀번호  : ");
		memberLoginPw = sc.nextLine().trim();
		
		Member foundMember = memberloginDupChk(memberLoginId);
		
		if (foundMember == null) {
			System.out.println("아이디가 다릅니다.");
			return;
		}
		if (foundMember.getMemberLoginPw().equals(memberLoginPw) == false) {
			System.out.println("비밀번호가 다릅니다.");
			return;
		}
		loginedMember = foundMember; //로그인 정보 저장
		System.out.println("로그인 성공");
	}
		
	private void doLogout() {

		if ( isLogined() == false) {
			System.out.println("로그인중이 아닙니다.");
			return;
		}
		
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}
	
	private boolean memberIdDupChk(String memberLoginId) {
		Member member = memberloginDupChk(memberLoginId);
		
		if (member != null) {
			return false;
		}
		return true;// 중복 아님
	}
	
	//로그인 아이디 체크
	private Member memberloginDupChk(String memberLoginId) {
		Member foundMember = null;

		for (Member member : members) {
			if (member.getMemberLoginId().equals(memberLoginId)) {
				foundMember = member;
				return foundMember;//아이디 존재
			}
		}
		return null;//아이디 존재안함
		
	}


	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 3개 생성");

		for (int i = 1; i <= 5; i++) {
			members.add(new Member(memberId++, Util.getDateStr(), "user" + i, "user" + i, "유저" + i));
		}
	}

}
