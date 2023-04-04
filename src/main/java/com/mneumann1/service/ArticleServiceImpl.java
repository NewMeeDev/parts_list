/**
 * 
 */
package com.mneumann1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mneumann1.model.Article;
import com.mneumann1.repository.ArticleRepository;

import jakarta.validation.Valid;

/**
 * @author MNEUMANN1
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository artRepo;
	
	
	@Override
	public List<Article> getAllArticles() {
		return artRepo.findAll();
	}

	
	@Override
	public Article getArticleById(Long id) {
		Optional<Article> article = artRepo.findById(id);
		
		if (article.isPresent()) {
			return article.get();
		} else {
			throw new RuntimeException("Der Artikel mit der ID: " + id + " wurde nicht gefunden!");
		}
		
	}

	
	@Override
	public Article createArticle(Article article) {
		return artRepo.save(article);
	}

	
	@Override
	public Article updateArticle(Article article) {
		return artRepo.save(article);
	}

	
	@Override
	public void deleteArticle(Long id) {
		artRepo.deleteById(id);
	}

}
