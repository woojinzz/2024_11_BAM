import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();
		int id = 4;
		Date now = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime1 = sdf1.format(now);
		articles.add(new Article(1, "test", "tttt", nowTime1));
		articles.add(new Article(2, "222", "2222", nowTime1));
		articles.add(new Article(3, "tes33t", "3333", nowTime1));

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

				Article article = new Article(id, title, body, nowTime1);
				articles.add(article);
//				articles.add(new Article(id, title, body));
				System.out.println(id + " 번 글이 생성되었습니다.");
				id++;

			}

			else if (cmd.equals("article list")) {

				if (articles.size() == 0) {
					System.out.println("존재하는 게시글이 없습니다.");
					continue;
				}

				System.out.println("번호	:	제목");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	:	%s\n", article.id, article.title);

				}
			}

			else if (cmd.startsWith("article detail ")) {
				String[] cmdBits = cmd.split(" ");
				int lastAticleId = 0;

				try {
					lastAticleId = Integer.parseInt(cmdBits[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				}

				Article foundArticle = null;

				for (Article article : articles) {
					if (article.id == lastAticleId) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d 번 게시물이 존재하지 않습니다.\n", lastAticleId);
					continue;
				}

				System.out.println("번호 : " + foundArticle.id);
				System.out.println("날짜 : " + foundArticle.nowTime1);
				System.out.println("제목 : " + foundArticle.title);
				System.out.println("내용 : " + foundArticle.body);

			} else if (cmd.startsWith("article modify ")) {

				String[] cmdBits = cmd.split(" ");
				int lastAticleId = Integer.parseInt(cmdBits[2]);
				Article foundArticle = null;

//				for (int i = 0; i <= articles.size(); i++) {
//					
//					foundArticle = articles.get(i);
//					break;
//				}

				for (Article article : articles) {
					if (article.id == lastAticleId) {
						foundArticle = articles.get(lastAticleId);
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d 번 게시물이 존재하지 않습니다.\n", lastAticleId);
					continue;
				}

				System.out.println("수정할 제목 : ");
				String title = sc.nextLine().trim();
				System.out.println("수정할 내용 : ");
				String body = sc.nextLine().trim();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d 번 게시물이 수정 되었습니다.\n", lastAticleId);

			} else if (cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int lastAticleId = 0;
				Article foundArticle = null;

				try {
					lastAticleId = Integer.parseInt(cmdBits[2]);
				} catch (NumberFormatException e) {
					System.out.println("명령어가 올바르지 않습니다.");
					continue;
				}


				int foundIndex = -1;
				
				for (int i= 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if(article.id == lastAticleId) {
						foundIndex = i;  
						break;
					}
				}
				
				if(foundIndex == -1) {
					System.out.printf("%d 번 게시물이 존재하지 않습니다.\n", lastAticleId);
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%d 번 게시물이 삭제되었습니다.\n", lastAticleId);

			}

			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}


}

class Article {
	int id;
	String title;
	String body;
	String nowTime1;

	Article(int id, String title, String body, String nowTime1) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.nowTime1 = nowTime1;

	}


}
