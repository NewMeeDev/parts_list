/**
 * 
 */
package com.mneumann1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mneumann1.model.Article;
import com.mneumann1.service.ArticleService;

import jakarta.validation.Valid;


/**
 * @author MNEUMANN1
 *
 */


@RestController
@RequestMapping("/api/v1/xml/articles")
public class ArticleXmlController {
	
	@Autowired
	ArticleService service;
	
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<Article>> getAllArticles() {
		return new ResponseEntity<List<Article>>(service.getAllArticles(), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Article> getArticleById(@PathVariable("id") Long id) {
		return new ResponseEntity<Article>(service.getArticleById(id), HttpStatus.OK);
	}
	
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Article> createArticle(@Valid @RequestBody Article newArticle) {
		return new ResponseEntity<Article>(service.createArticle(newArticle), HttpStatus.CREATED);
	}
	
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Article> updateArticle(@PathVariable("id") Long id, @Valid @RequestBody Article articleToUpdate) {
		articleToUpdate.setId(id);
		return new ResponseEntity<Article>(service.updateArticle(articleToUpdate), HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") Long id) {
		service.deleteArticle(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

}
