import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
		int cnt = 0; 
		
		while(true) {
			
			System.out.println("명령어) ");
			String cmd = sc.nextLine().trim();
			
			if (cmd.equals("article list")) {
				if (cnt == 0) {
					System.out.println("게시글이 없습니다.");
				}
				continue;
			}
			
		
			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}
			if (cmd.equals("exit")) {
				break;
			}
			
			else if (cmd.equals("article write")) {
				System.out.println("제목 : ");
				String title = sc.nextLine();
				System.out.println("내용 : ");
				String body = sc.nextLine();
				cnt++;
				System.out.println(cnt + " 번 글이 생성되었습니다.");
			
			}
			
			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
		sc.close();
		System.out.println("== 프로그램 끝 ==");
	}
}
