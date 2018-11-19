/**
 * 
 */
package ru.hotel.service.exceptions;

/**
 * Исключение генерится при ошибке редактирования объекта
 * 
 * @author dulev
 *
 */
public class UpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1450879107837157252L;

	public UpdateException(String message) {
		super(message);
	}

}
