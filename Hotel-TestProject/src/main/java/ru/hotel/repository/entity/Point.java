package ru.hotel.repository.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс описывает геолокацию
 * @author dulev
 *
 */
// Можно было использовать спринговый поинт, но там координаты описаны примитивным типом
// Они по умалчанию в объекте Embedded являются notNull
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Point implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double x;
	private Double y;
	
}
