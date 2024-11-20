package com.koreaIT.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.Container.Container;
import com.koreaIT.BAM.util.Util;
import com.koreaIT.dto.Article;
import com.koreaIT.dto.Member;

public class ArticleController extends Controller {

	private List<Article> articles;
	private List<Member> members;
	
	public ArticleController(Scanner sc) {
		this.sc = sc;
		this.articles = Container.articles;
		this.members = Container.members;
		this.lastId = 1;
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
		
		if (isLogined() == false) {
			System.out.println("로그인을 해주세요.");
			return;
		}
		
		System.out.println("제목 : ");
		String title = sc.nextLine().trim();
		System.out.println("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(lastId, Util.getDateStr(), loginedMember.getId(), title, body, 0);
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

	
		System.out.println("번호	:	날짜			:	작성자 	: 	제목	:	조회수 ");
		for (int i = searchList.size() - 1; i >= 0; i--) {
			Article article = searchList.get(i);  
			String weiterLoginId = getLoginIdByMemberId(article.getMemberId());
			
			System.out.printf("%d	:	%s	:	%s	:	%s	:	%d \n", article.getId(), article.getRegDate(), weiterLoginId,
					article.getTitle(), article.getViews());
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
		
		String weiterLoginId = getLoginIdByMemberId(foundArticle.getMemberId());
		

		System.out.println("번호 : " + foundArticle.getId());
		System.out.println("날짜 : " + foundArticle.getRegDate());
		System.out.println("작성자 : " 	+ weiterLoginId); 
		System.out.println("제목 : " + foundArticle.getTitle());
		System.out.println("내용 : " + foundArticle.getBody());
		System.out.println("조회수  : " + foundArticle.getViews());
		

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
	
	private String getLoginIdByMemberId(int memberId) {
		for (Member member : members) {
			if ( memberId == (member.getId())) {
				 return member.getLoginId();
			}
		}
		
		return null;
	}


	public void makeTestData() {
		System.out.println("테스트용 게시글 데이터 5개 생성");

		for (int i = 1; i <= 5; i++) {
			articles.add(new Article(lastId++, Util.getDateStr(), (int) (Math.random() * 3) + 1, "제목" + i, "내용" + i, i * 10));
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
