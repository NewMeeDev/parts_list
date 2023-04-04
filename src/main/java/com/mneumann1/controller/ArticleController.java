/**
 * 
 */
package com.mneumann1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mneumann1.model.Article;
import com.mneumann1.service.ArticleService;

import jakarta.validation.Valid;


/**
 * @author MNEUMANN1
 *
 */
@RestController
public class ArticleController {
	
	@Autowired
	ArticleService service;
	
	
	@GetMapping("/articles")
	public List<Article> getAllArticles() {
		return service.getAllArticles();
	}
	
	
	@GetMapping("/articles/{id}")
	public Article getArticleById(@PathVariable("id") Long id) {
		return service.getArticleById(id);
		//return new ResponseEntity<Article>(service.getArticleById(id), HttpStatus.OK);
	}
	
	
	@PostMapping("/articles")
	public Article createArticle(@Valid @RequestBody Article newArticle) {
		return service.createArticle(newArticle);
	}
	
	
	@PutMapping("/articles/{id}")
	public Article updateArticle(@PathVariable("id") Long id, @Valid @RequestBody Article articleToUpdate) {
		articleToUpdate.setId(id);
		return service.updateArticle(articleToUpdate);
	}
	
	
	@DeleteMapping("/articles/{id}")
	public void deleteArticle(@PathVariable("id") Long id) {
		service.deleteArticle(id);
	}
}
