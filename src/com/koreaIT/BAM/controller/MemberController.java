package com.koreaIT.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.dto.Member;
import com.koreaIT.BAM.service.MemberService;
import com.koreaIT.BAM.util.Util;

public class MemberController extends Controller {

	private MemberService memberService;

	public MemberController(Scanner sc) {

		this.sc = sc;
		this.memberService = new MemberService();
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

		String loginId;
		String loginPw;
		String loginPwChk;
		String loginName;
		System.out.println("======= 회원가입 페이지 =========");

		while (true) {
			System.out.println("아이디  : ");
			loginId = sc.nextLine().trim();

			if (loginId.length() == 0) {
				System.out.println("아이디를 입력해 주세요.");
				continue;
			}

			if (memberService.loginIdDupChk(loginId) == false) {
				System.out.printf("%s 는 중복된 아이디 입니다.\n", loginId);
				continue;
			}
			System.out.printf("%s 는 사용 가능한 아이디 입니다.\n", loginId);
			break;
		}

		while (true) {
			System.out.println("비밀번호 : ");
			loginPw = sc.nextLine().trim();

			if (loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해 주세요");
				continue;
			}

			System.out.println("비밀번호 확인 : ");
			loginPwChk = sc.nextLine().trim();

			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			break;
		}

		while (true) {
			System.out.println("이름 : ");
			loginName = sc.nextLine().trim();

			if (loginName.length() == 0) {
				System.out.println("이름을 입력해 주세요");
				continue;
			}
			break;
		}

		int memberNumber = memberService.joinMember(loginId, loginPw, loginName);

		System.out.println("[" + loginId + "] 회원님의 가입이 완료되었습니다.");
		lastId++;

	}

	private void doLogin() {

		String memberLoginId;
		String memberLoginPw;

		System.out.println("아이디  : ");
		memberLoginId = sc.nextLine().trim();
		System.out.println("비밀번호  : ");
		memberLoginPw = sc.nextLine().trim();

		Member foundMember = memberService.getMemberByLoginId(memberLoginId);

		if (foundMember == null) {
			System.out.println("아이디가 다릅니다.");
			return;
		}
		if (foundMember.getLoginPw().equals(memberLoginPw) == false) {
			System.out.println("비밀번호가 다릅니다.");
			return;
		}
		loginedMember = foundMember; // 로그인 정보 저장
		System.out.println("로그인 성공");
	}

	private void doLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 3개 생성");

		for (int i = 1; i <= 3; i++) {
			memberService.joinMember("user" + i, "user" + i, "유저" + i);
//			members.add(new Member(lastId++, Util.getDateStr(), "user" + i, "user" + i, "유저" + i));
		}
	}

}
