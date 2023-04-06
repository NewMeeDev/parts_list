/**
 * 
 */
package com.mneumann1.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author MNEUMANN1
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchModel {

	private String searchTermForArticleName;
	private String searchTermForArticleDescription;

	@DecimalMin(value = "0", inclusive = true, message="Das Minimum des Preisfilters liegt bei genau 0 Cent!")
	@DecimalMax(value = "9999.98", inclusive = true, message="Das Minimum des Preisfilters darf 9999.98 EURO nicht überschreiten!")
	private BigDecimal searchPriceRangeFrom = new BigDecimal(0);
	
	@DecimalMin(value = "0", inclusive = true, message="Das Maximum des Preisfilters muss mindestens bei 0 Cent liegen!")
	@DecimalMax(value = "9999.99", inclusive = true, message="Das Maximum des Preisfilters darf 9999.99 EURO nicht überschreiten!")
	private BigDecimal searchPriceRangeTo = new BigDecimal(0);
	
	
}
