package com.koreaIT.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class ArticleDao {

	private int lastId;
	private List<Article> articles;

	public ArticleDao() {
		this.lastId = 1;
		this.articles = new ArrayList<>();
	}

	public int getLastId() {
		return lastId;
	}

	public void writeArticle(int memberId, String body, String title, int viewCnt) {
		articles.add(new Article(lastId, Util.getDateStr(), memberId, title, body, viewCnt));
		lastId++;
	}

	public Article getArticelById(int id) {

		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public void modifyArticle(Article foundArticle, String title, String body) {
		foundArticle.setTitle(title);
		foundArticle.setTitle(body);
	}

	public void deleteArticle(Article foundArticle) {
		articles.remove(foundArticle); // 아티클 안에있는 리무브라는 메서드를 사용해서 파운드아티글을 제거

	}

	public void increaseViewCnt(Article foundArticle) {
		foundArticle.increaseViewCnt();
	}

	public List<Article> getPrintArticles(String searchKeyword) {

		if (searchKeyword.length() > 0) {
			System.out.println("검색어 : " + searchKeyword);

			List<Article> printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.getTitle().contains(searchKeyword)) {
					printArticles.add(article);
				}

			}
			return printArticles;
		}
		return articles;
	}

}
