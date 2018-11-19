/**
 * 
 */
package ru.hotel.service.exceptions;

/**
 * Исключение генерится при ошибке удаления объекта
 * 
 * @author dulev
 *
 */
public class DeleteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5519961235712237499L;

	public DeleteException(String message) {
		super(message);
	}

}
