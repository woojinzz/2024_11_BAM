package com.koreaIT.BAM.dao;

import java.util.List;

import com.koreaIT.BAM.container.Container;
import com.koreaIT.BAM.dto.Article;
import com.koreaIT.BAM.util.Util;

public class ArticleDao {
	
	private int lastId;
	private List<Article> article;
	
	public ArticleDao() {
		this.lastId = 1;
		this.article = Container.articles;
	}

	public int writeArticle(int memberId, String body, String title, int viewCnt) {
		article.add(new Article(lastId, Util.getDateStr(), memberId, title, body, viewCnt));
		return lastId++;
	}

}
