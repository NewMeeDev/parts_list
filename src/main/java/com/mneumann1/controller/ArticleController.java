/**
 * 
 */
package com.mneumann1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mneumann1.model.Article;
import com.mneumann1.model.SearchModel;
import com.mneumann1.service.ArticleService;

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

	
	@GetMapping
	public String showAllArticles(Model model) {
		List<Article> articles = service.getAllArticles();
		model.addAttribute("articles", articles);
		model.addAttribute("title", "Alle verfügbaren Artikel:");
		return "articles.html";
	}
	
	
	@GetMapping("/showNewArticleForm")
	public String showNewArticleForm(Model model) {
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
	
	
	@GetMapping("/showSearchForm")
	public String showSearchForm(Model model) {
		model.addAttribute("searchModel", new SearchModel());
		return "searchForm.html";
	}
	
	
	@PostMapping("/search")
	public String search(@Valid SearchModel searchModel, BindingResult bindingResult, Model model) { 
		
		// if validation fails
		if (bindingResult.hasErrors()) {
		
			return "searchForm.html";
			
		} else {
		List<Article> articles = service.searchArticles(searchModel.getSearchTermForArticleName(), 
				searchModel.getSearchTermForArticleDescription(), searchModel.getSearchTermForArticleTrader(), 
				searchModel.getSearchPriceRangeFrom(), searchModel.getSearchPriceRangeTo());
		model.addAttribute("articles", articles);
		return "articles.html";
		}
	}
	
	
	@GetMapping("/admin")
	public String showAdminPage(Model model) {
		List<Article> articles = service.getAllArticles();
		model.addAttribute("articles", articles);
		return "articlesAdmin.html";
	}
	
	
	@PostMapping("/delete")
	public String delete(@Valid Article article, BindingResult bindingResult, Model model) {
		
		service.deleteArticle(article.getId());
		
		List<Article> articles = service.getAllArticles();
		
		model.addAttribute("articles", articles);
		
		return "articlesAdmin.html";
	}
	
	
	@PostMapping("/edit")
	public String editArticle(@Valid Article article, BindingResult bindingResult, Model model) {	
		model.addAttribute("article", article);
		return "editForm.html";
	}
	
	
	@PostMapping("/doUpdate")
	public String doUpdate(@Valid Article updateArticle, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			List<ObjectError> list = bindingResult.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.toString());
			}
			return "editForm.html";
			
		} else {
			service.updateArticle(updateArticle);
			
			List<Article> articles = service.getAllArticles();		
			model.addAttribute("articles", articles);
			return "articlesAdmin.html";
		}
	}
	
}
