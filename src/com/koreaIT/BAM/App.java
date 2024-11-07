package com.koreaIT.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.BAM.util.Util;
import com.koreaIT.dto.Article;

public class App {

	private List<Article> articles;
	private int lastAticleId; // 아이디

	public App() {
		this.articles = new ArrayList<>();
		this.lastAticleId = 1;
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");
		makeTestData();
		Scanner sc = new Scanner(System.in);

		int views = 0;// 조회수
		String regDate = Util.getDateStr();

		while (true) {

			System.out.println("명령어) ");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("article write")) {
				System.out.println("제목 : ");
				String title = sc.nextLine().trim();
				System.out.println("내용 : ");
				String body = sc.nextLine();
//				String time = now.toString();

				Article article = new Article(lastAticleId, Util.getDateStr(), title, body, views);
				articles.add(article);
//				articles.add(new Article(id, title, body));
				System.out.println(lastAticleId + " 번 글이 생성되었습니다.");
				lastAticleId++;

			}

			else if (cmd.startsWith("article list")) {

				if (articles.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다.");
					continue;
				}
				String search = cmd.substring("article list".length()).trim();

				List<Article> searchList = articles; // 검색 결과 저장

				if (search.length() != 0) {
					searchList = new ArrayList<>(); //초기화 
					
					System.out.println("검색어 :" + search);

					for (Article ar : articles) {
						if (ar.getTitle().contains(search)) { //검색어 포함되어 있는지 확인
							searchList.add(ar); // list 에 값 넣을려면 add
						}
					}
				}

				if (searchList.size() == 0) {
					System.out.println("검색된 게시글이 없습니다.");
					continue;
				}

				System.out.println("번호	:	제목	:	날짜		:		조회수");
				for (int i = searchList.size() - 1; i >= 0; i--) {
					Article article = searchList.get(i);
					System.out.printf("%d	:	%s	:	%s	:	%d\n", article.getId(), article.getTitle(),
							article.getRegDate(), article.getViews());
				}
			}

			else if (cmd.startsWith("article detail ")) {

				int id = getCmdNum(cmd);
				if (id == 0) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				}
				Article foundArticle = getArticelById(id);

				if (foundArticle == null) {
					System.out.printf("%d 번 게시물이 존재하지 않습니다.\n", id);
					continue;
				}

				foundArticle.increaseViewCnt(); // 메서드로 분리
				System.out.println("번호 : " + foundArticle.getId());
				System.out.println("날짜 : " + foundArticle.getRegDate());
				System.out.println("제목 : " + foundArticle.getTitle());
				System.out.println("내용 : " + foundArticle.getBody());
				System.out.println("조회수  : " + foundArticle.getViews());

			} else if (cmd.startsWith("article modify ")) {

				int id = getCmdNum(cmd);
				if (id == 0) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
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
					continue;
				}

				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine().trim();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine().trim();

				foundArticle.setTitle(title);
				foundArticle.setBody(body);
				foundArticle.setRegDate(regDate);

				System.out.println(id + "번 게시물이 수정되었습니다");

			} else if (cmd.startsWith("article delete ")) {

				int id = getCmdNum(cmd);
				if (id == 0) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
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
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%d 번 게시물이 삭제되었습니다.\n", id);

			}

			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}

	private Article getArticelById(int id) {

		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
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
	}

	private void makeTestData() {
		System.out.println("테스트용 게시글 데이터 3개 생성");
//		articles.add(new Article(lastAticleId++, Util.getDateStr(), "제목1", "내용1", 10));
//		articles.add(new Article(lastAticleId++, Util.getDateStr(), "제목2", "내용2", 20));
//		articles.add(new Article(lastAticleId++, Util.getDateStr(), "제목3", "내용3", 30));

		for (int i = 1; i <= 5; i++) {
			articles.add(new Article(lastAticleId++, Util.getDateStr(), "제목" + i, "내용" + i, i * 10));
		}
	}

}
