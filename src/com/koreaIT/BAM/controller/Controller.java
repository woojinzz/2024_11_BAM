package com.koreaIT.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.koreaIT.dto.Member;

public abstract class Controller {
	
	public Scanner sc;
	public int lastId; // 게시글 아이디
	public String cmd;
	public static Member loginedMember;

	
	// 오버라이딩해서 사용할거여서 바디가 필요없음(추상메서드)
	// abstract (엡서트) 워딩 붙이기
	// 한개 이상의 추상 메서드를 가진 클래스는 무조건 추상 클래스로 만들어야함
	// 추상클래스를 상속받으면 그 안에 있는 추상 메서드를 무조건 오버라이딩을 해야 에러 안남
	// @Override 어노텐션 달아야함
	public abstract void doAction(String cmd, String methodName);
	public abstract void makeTestData();
	
	public boolean isLogined() {
		return loginedMember != null; //로그인 한 상태 static 변수여서 사용 가능
	}
}
	
	


