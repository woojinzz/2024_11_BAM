import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static List<Article> articles;
	static int lastAticleId; // 아이디
	
	static {
		articles = new ArrayList<>();
		lastAticleId = 1;
	}
	
	
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		makeTestData();
		Scanner sc = new Scanner(System.in);
		
		int views = 0;//조회수
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

				Article article = new Article(lastAticleId, regDate, title, body, views);
				articles.add(article);
//				articles.add(new Article(id, title, body));
				System.out.println(lastAticleId + " 번 글이 생성되었습니다.");
				lastAticleId++;

			}

			else if (cmd.equals("article list")) {

				if (articles.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다.");
					continue;
				}

				System.out.println("번호	:	제목	:	날짜		:		조회수");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	:	%s	:	%s	:	%d\n", article.lastAticleId, article.title, article.regDate, article.views);

				}
			}

			else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				int id = 0;
				
				

				try {
					id = Integer.parseInt(cmdBits[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.lastAticleId == id) {
						foundArticle = article;
						break;
						
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d 번 게시물이 존재하지 않습니다.\n", id);
					continue;
				}
				
				foundArticle.increaseViewCnt();	//메서드로 분리
				System.out.println("번호 : " + foundArticle.lastAticleId);
				System.out.println("날짜 : " + foundArticle.regDate);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);
				System.out.println("조회수  : " + foundArticle.views);

			} else if (cmd.startsWith("article modify ")) {
				String[] cmdBits = cmd.split(" ");

				int id = 0;
				
				try {
					id = Integer.parseInt(cmdBits[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다");
					continue;
				}
				
				Article foundArticle = null;
				
				for (Article article : articles) {
					if (article.lastAticleId == id) {
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
				
				foundArticle.title = title;
				foundArticle.body = body;
				foundArticle.regDate = regDate;
			
				System.out.println(id + "번 게시물이 수정되었습니다");
				
			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int id = 0;
//				Article foundArticle = null;

				try {
					id = Integer.parseInt(cmdBits[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				}


				int foundIndex = -1;
				
				for (int i= 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if(article.lastAticleId == id) {
						foundIndex = i;  
						break;
					}
				}
				
				if(foundIndex == -1) {
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

	private static void makeTestData() {
		System.out.println("테스트용 게시글 데이터 3개 생성");
//		articles.add(new Article(lastAticleId++, Util.getDateStr(), "제목1", "내용1", 10));
//		articles.add(new Article(lastAticleId++, Util.getDateStr(), "제목2", "내용2", 20));
//		articles.add(new Article(lastAticleId++, Util.getDateStr(), "제목3", "내용3", 30));
		
		for (int i = 1; i <= 5; i++) {
			articles.add(new Article(lastAticleId++, Util.getDateStr(), "제목" + i, "내용" + i, i * 10));
		}
	}
}

class Article {
	int lastAticleId;
	String regDate;
	String title;
	String body;
	int views; 


	Article(int lastAticleId, String regDate, String title, String body, int views) {
		this.lastAticleId = lastAticleId;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.views = views;
	

	}
	
	void increaseViewCnt() {
		this.views++;
		
	}


}
