/**
 * 
 */
package com.mneumann1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mneumann1.model.Article;
import com.mneumann1.service.ArticleService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

/**
 * @author MNEUMANN1
 *
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {
	
	@Autowired
	ArticleService service;

	
	@GetMapping("/")
	public String showAllArticles(Model model) {
		List<Article> articles = service.getAllArticles();
		model.addAttribute("articles", articles);
		model.addAttribute("title", "Alle verfügbaren Artikel:");
		return "articles.html";
	}
	
	
	@GetMapping("/showNewArticleForm")
	public String showNewForm(Model model) {
		model.addAttribute("article", new Article());
		return "addNewArticleForm.html";
	}
	
	
	@PostMapping("/addNew")
	public String addNew(@Valid Article newArticle, BindingResult bindingResult, Model model) {
		
		// if validation fails
		if (bindingResult.hasErrors()) {
		
			return "addNewArticleForm.html";
			
		} else {
			
			newArticle.setId(null);
			
			// add to the database
			service.createArticle(newArticle);
			
			// get all orders from database
			List<Article> articles = service.getAllArticles();
			
			// show all orders page
			model.addAttribute("articles", articles);
					
			return "articles.html";
		}
	}
	
	
}
