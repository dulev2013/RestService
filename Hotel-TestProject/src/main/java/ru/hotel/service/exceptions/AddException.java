/**
 * 
 */
package ru.hotel.service.exceptions;

/**
 * Исключение генрится при добавлении объекта
 * 
 * @author dulev
 *
 */
public class AddException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -286710177003255916L;

	public AddException(String message) {
		super(message);

	}

	public AddException(String message, Throwable cause) {
		super(message, cause);

	}

}
