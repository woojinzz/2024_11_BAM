package com.koreaIT.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.util.Util;
import com.koreaIT.dto.Article;
import com.koreaIT.dto.Member;

public class ArticleController extends Controller {

	private List<Article> articles;
	private int views;// 조회수
	private String regDate;


	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articles = new ArrayList<>();
		this.lastId = 1;
//		this.views = 0;
//		this.regDate = Util.getDateStr();
		
		
	}

	@Override
	public void doAction(String cmd, String methodName) {
		this.cmd = cmd;

		switch (methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
		}
	}

	public void doWrite() {
		
		int memberId = 0;
		
		if (isLogined() == false) {
			System.out.println("로그인을 해주세요.");
			return;
		}
		
		System.out.println("제목 : ");
		String title = sc.nextLine().trim();
		System.out.println("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(lastId, Util.getDateStr(), title, body, views , loginedMember.getMemberId());
		articles.add(article);
//		articles.add(new Article(id, title, body));
		System.out.println(lastId + " 번 글이 생성되었습니다.");
		lastId++;

	}

	public void showList() {
		if (articles.size() == 0) {
			System.out.println("존재하는 게시글이 없습니다.");
			return;
		}
		String search = cmd.substring("article list".length()).trim();

		List<Article> searchList = articles; // 검색 결과 저장

		if (search.length() != 0) {
			searchList = new ArrayList<>(); // 초기화

			System.out.println("검색어 :" + search);

			for (Article ar : articles) {
				if (ar.getTitle().contains(search)) { // 검색어 포함되어 있는지 확인
					searchList.add(ar); // list 에 값 넣을려면 add
				}
			}
		}

		if (searchList.size() == 0) {
			System.out.println("검색된 게시글이 없습니다.");
			return;
		}

		System.out.println("번호	:	제목	:	날짜		:		조회수 	:	작성자아이디");
		for (int i = searchList.size() - 1; i >= 0; i--) {
			Article article = searchList.get(i);
			System.out.printf("%d	:	%s	:	%s	:	%d	: 		%d\n", article.getId(), article.getTitle(),
					article.getRegDate(), article.getViews(), article.getMemberId());
		}
	}

	public void showDetail() {
		int id = getCmdNum(cmd);
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}
		Article foundArticle = getArticelById(id);

		if (foundArticle == null) {
			System.out.printf("%d 번 게시물이 존재하지 않습니다.\n", id);
			return;
		}

		foundArticle.increaseViewCnt(); // 메서드로 분리
		System.out.println("번호 : " + foundArticle.getId());
		System.out.println("날짜 : " + foundArticle.getRegDate());
		System.out.println("제목 : " + foundArticle.getTitle());
		System.out.println("내용 : " + foundArticle.getBody());
		System.out.println("조회수  : " + foundArticle.getViews());
		System.out.println("작성자아이디  : " + foundArticle.getMemberId());

	}

	public void doModify() {
		
		if (isLogined() == false) {
			System.out.println("로그인을 해주세요.");
			return;
		}
		
		int id = getCmdNum(cmd);
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}
		Article foundArticle = getArticelById(id);

		for (Article article : articles) {
			if (article.getId() == id) {
				foundArticle = article;
				break;
			}
		}

		if (foundArticle == null) {
			System.out.println(id + "번 게시물이 존재하지 않습니다");
			return;
		}

		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine().trim();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine().trim();

		foundArticle.setTitle(title);
		foundArticle.setBody(body);
		foundArticle.setRegDate(regDate);

		System.out.println(id + "번 게시물이 수정되었습니다");

	}

	public void doDelete() {
		
		if (isLogined() == false) {
			System.out.println("로그인을 해주세요.");
			return;
		}

		int id = getCmdNum(cmd);
		if (id == 0) {
			System.out.println("명령어가 올바르지 않습니다.");
			return;
		}

		int foundIndex = -1;

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.getId() == id) {
				foundIndex = i;
				break;
			}
		}

		if (foundIndex == -1) {
			System.out.printf("%d 번 게시물이 존재하지 않습니다.\n", id);
			return;
		}

		articles.remove(foundIndex);
		System.out.printf("%d 번 게시물이 삭제되었습니다.\n", id);

	}

	public Article getArticelById(int id) {

		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void makeTestData() {
		System.out.println("테스트용 게시글 데이터 5개 생성");

		for (int i = 1; i <= 5; i++) {
			articles.add(new Article(lastId++, Util.getDateStr(), "제목" + i, "내용" + i, i * 10, i));
		}
	}

	private int getCmdNum(String cmd) {

		String[] cmdBits = cmd.split(" ");

		int id = 0;

		try {
			id = Integer.parseInt(cmdBits[2]);
			return id;
		} catch (NumberFormatException e) {
			return 0;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

}
