/**
 * 
 */
package com.mneumann1.service;

import java.util.List;

import com.mneumann1.model.Article;

/**
 * @author MNEUMANN1
 *
 */
public interface ArticleService {

	List<Article> getAllArticles();
	
	<Optional>Article getArticleById(Long id);
	
	Article createArticle(Article article);
	
	Article updateArticle(Article article);
	
	void deleteArticle(Long id);
}
