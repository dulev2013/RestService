package ru.hotel.repository.entity;

import java.io.Serializable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс описывает сайт отеля
 * @author Антон
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Site implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Pattern(regexp = "^www\\.([a-z0-9])+(\\.[a-z]{2,3})$")
	private String label;
	@URL
	private String url;
	
}
