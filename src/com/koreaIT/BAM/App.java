package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.controller.ArticleController;
import com.koreaIT.BAM.controller.MemberController;
import com.koreaIT.BAM.util.Util;
import com.koreaIT.dto.Article;
import com.koreaIT.dto.Member;

public class App {
	public void run() {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);

		memberController.makeMemberTestData();
		articleController.makeArticleTestData();

		while (true) {

			System.out.println("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			String[] cmdBits = cmd.split(" ");
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];

			if (controllerName.equals("article")) {
				articleController.doAction(cmd, methodName);

			} else if (controllerName.equals("member")) {
				memberController.doAction(cmd, methodName);
			}

			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
		sc.close();
		System.out.println("== 프로그램 종료 ==");
	}

}
