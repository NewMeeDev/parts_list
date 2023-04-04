/**
 * 
 */
package com.mneumann1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mneumann1.model.Article;

/**
 * @author MNEUMANN1
 *
 */
@Repository
//wenn Paging und Sorting nicht benötigt wird, dann kann man hier auch JpaRepository<T, ID> als Basisklasse verwenden 
//dann sind die Methoden im Service und im Controller schlanker (weniger Parameter)
public interface ArticleRepository extends JpaRepository<Article, Long> {
	
	// add special repo-methods here

}
