package com.koreaIT.dto;

public class Article {
	private int id;
	private String regDate;
	private String title;
	private String body;
	private int views;
	private int memberId; 


	public int getId() {
		return id;
	}

	public void setLastAticleId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Article(int id, String regDate, String title, String body, int views, int memberId) {
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.views = views;
		this.memberId = memberId;
	

	}
	
	public void increaseViewCnt() {
		this.views++;
	}


}