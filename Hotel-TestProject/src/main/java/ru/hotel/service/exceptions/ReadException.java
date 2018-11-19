/**
 * 
 */
package ru.hotel.service.exceptions;

/**
 * Исключение генерится при ошибке чтения объектов
 * 
 * @author dulev
 *
 */
public class ReadException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 851513857409780335L;

	public ReadException(String message) {
		super(message);
	}

}
