import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");
		Scanner sc = new Scanner(System.in);
	
		while(true) {
			
			System.out.println("명령어) ");
			String cmd = sc.nextLine();
			
			if (cmd.equals("Article")) {
				
				
				
//				break;
			}
			
			if (cmd.equals("exit")) {
				break;
			}
			
		}
		sc.close();
		System.out.println("== 프로그램 끝 ==");
		
	}
}
