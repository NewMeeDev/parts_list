/**
 * 
 */
package com.mneumann1.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
			return null;
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
	
	@Override
	public List<Article> searchArticles(String searchTermForArticleName, 
			String searchTermForArticleDescription, String searchTermForArticleTrader, BigDecimal searchPriceRangeFrom, BigDecimal searchPriceRangeTo) {
		
		List<Article> articles = artRepo.findAll();
		List<Article> matches = new ArrayList<>();
				
		for (Article article : articles) {
			if(article.getName().toLowerCase().contains(searchTermForArticleName.toLowerCase())) {
				if(article.getDescription().toLowerCase().contains(searchTermForArticleDescription.toLowerCase())) {
					if(article.getTrader().toLowerCase().contains(searchTermForArticleTrader.toLowerCase())) {
						if(searchPriceRangeTo.compareTo(BigDecimal.ZERO) == 0  && searchPriceRangeTo.compareTo(BigDecimal.ZERO) == 0) {
							searchPriceRangeFrom = new BigDecimal(0);
							searchPriceRangeTo = new BigDecimal(9999.99);
						}
						
						if (article.getPrice().compareTo(searchPriceRangeTo) <= 0 && article.getPrice().compareTo(searchPriceRangeFrom) >= 0) {
							matches.add(article);
						}
					}
							
				}
			}		
		}
		return matches;
	}
}
