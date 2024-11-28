package com.koreaIT.BAM.service;

import com.koreaIT.BAM.dao.ArticleDao;

public class ArticleService {
	
	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = new ArticleDao(); //객체 만들기
	}

	public int writeArticle(int memberId, String body, String title, int viewCnt) {
		return articleDao.writeArticle(memberId, title, body, viewCnt);
		
		
	}

}
