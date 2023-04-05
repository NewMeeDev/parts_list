/**
 * 
 */
package com.mneumann1.model;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author MNEUMANN1
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="article") // wenn Tabellenname <> Entity-Klassenname, dann muss man so dem Entity den Namen der Tabelle zuweisen
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message="Es muss eine Artikelbezeichung eingegeben werden!")
	private String name;
	
	@NotBlank(message="Bitte geben Sie einen kurzen Beschreibungstext ein!")
	private String description;

	
	@NotBlank(message = "Es muss ein Händler eingegeben werden!")
	private String trader; 
	
	
	@NotNull(message="Es muss ein Preis eingegeben werden!")
	@DecimalMin(value = "0.01", inclusive = true, message="Der Preis muss mindestens 1 Cent betragen!")
	@DecimalMax(value = "9999.99", inclusive = true, message="Der Preis darf maximal 9999,99 EURO betragen!")
	private BigDecimal price;
	
	
	@CreationTimestamp
	@Column(name="created_at", nullable=false, updatable=false)
	private Date createdAt;
	
	
	@UpdateTimestamp
	@Column(name="updated_at")
	private Date updatedAt;

}
