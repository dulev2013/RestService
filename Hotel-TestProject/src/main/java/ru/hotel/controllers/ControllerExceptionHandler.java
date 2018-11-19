package ru.hotel.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.hotel.service.exceptions.DeleteException;
import ru.hotel.service.exceptions.ReadException;
import ru.hotel.service.exceptions.UpdateException;

/**
 * Класс, который является обработчиком всех исключений, которые возникают в
 * контроллерах сервера
 * @dulev
 *
 */
@RestControllerAdvice(basePackages = { "ru.hotel.controllers" })
public class ControllerExceptionHandler {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Обработка всех исключений
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public void handleAllException(HttpServletRequest request, Exception ex) {
		log.error("Исключение при вызове метода: " + request.getRequestURI() + ". " + ex.getMessage(), ex);
	}

	/**
	 * Обработка исключения при удалении отеля
	 * @param request
	 * @param ex
	 */
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(DeleteException.class)
	public void handleDeleteException(HttpServletRequest request, Exception ex) {
		log.warn("Исключение при вызове метода: " + request.getRequestURI() + ". " + ex.getMessage());
	}

	/**
	 * Обработка исключения при редактировании отеля
	 * @param request
	 * @param ex
	 */
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(UpdateException.class)
	public void handleUpdateException(HttpServletRequest request, Exception ex) {
		log.warn("Исключение при вызове метода: " + request.getRequestURI() + ". " + ex.getMessage());
	}
	
	/**
	 * Обработка исключения при поиске отеля
	 * @param request
	 * @param ex
	 */
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(ReadException.class)
	public void handleReadException(HttpServletRequest request, Exception ex) {
		log.warn("Исключение при вызове метода: " + request.getRequestURI() + ". " + ex.getMessage());
	}

}
