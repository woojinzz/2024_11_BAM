package com.koreaIT.BAM.service;

import java.util.List;

import com.koreaIT.BAM.dao.ArticleDao;
import com.koreaIT.BAM.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = new ArticleDao(); // 객체 만들기
	}

	public int writeArticle(int memberId, String body, String title, int viewCnt) {
		return articleDao.writeArticle(memberId, title, body, viewCnt);

	}

	public Article getArticelById(int id) {
		return articleDao.getArticelById(id);
	}

	public void modifyArticle(Article foundArticle, String title, String body) {
		
		articleDao.modifyArticle(foundArticle, title, body);
	}

	public void deleteArticle(Article foundArticle) {
		articleDao.deleteArticle(foundArticle);
		
	}

	public void increaseViewCnt(Article foundArticle) {
		articleDao.increaseViewCnt(foundArticle);
		
	}

	public List<Article> getPrintArticles(String searchKeyword) {
		return articleDao.getPrintArticles(searchKeyword);
	}




}
