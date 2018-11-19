package ru.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import ru.hotel.DTO.HotelDTO;
import ru.hotel.logging.AnnotationLogArround;
import ru.hotel.service.IHotelService;
import ru.hotel.service.exceptions.ReadException;


/**
 * Класс описывает контроллер для доступа к отелям
 * @author dulev
 *
 */
@RestController
@RequestMapping(PropertiesHotelServerUri.mappingBase)
@ResponseStatus(code = HttpStatus.OK)
public class HotelController {

	@Autowired
	private IHotelService service;


	/**
	 * Метод добавляет новый отель
	 * 
	 * @param value
	 *            Отель
	 * @return Идентификатор отеля в БД
	 */
	@AnnotationLogArround
	@ApiOperation("Создание нового отеля")
	@RequestMapping(value = PropertiesHotelServerUri.add, method = RequestMethod.POST)
	public String add(@Validated(HotelDTO.New.class) @RequestBody HotelDTO value) {
		return service.add(value);
	}

	/**
	 * Метод обновляет отель
	 * 
	 * @param value	
	 * 			Отредактированный отель
	 */
	@AnnotationLogArround
	@ApiOperation("Редактирование отеля")
	@RequestMapping(value = PropertiesHotelServerUri.update, method = RequestMethod.PUT)
	public void update(@Validated(HotelDTO.Update.class) @RequestBody HotelDTO value) {
		service.update(value);
	}

	/**
	 * Метод удаляет отель.
	 * 
	 * @param id
	 * 		Идентификатор отеля
	 */
	@AnnotationLogArround
	@ApiOperation("Удаление отеля")
	@RequestMapping(value = PropertiesHotelServerUri.delete, method = RequestMethod.DELETE)
	public void deleteById(@RequestParam(value = "id", required = true) String id) {
		service.delete(id);
	}


	/**
	 * Метод возвращает отель по идентификатору
	 * 
	 * @param id
	 *            Идентификатор
	 * @return Отель или null, если не найден
	 */
	@AnnotationLogArround
	@ApiOperation("Поиск отеля по идентификатору")
	@RequestMapping(value = PropertiesHotelServerUri.findById, method = RequestMethod.GET)
	@Transactional(readOnly = true)
	public HotelDTO findById(@RequestParam(value = "id", required = true) String id)  {
		HotelDTO result = service.findById(id);
		if (result==null)
			throw new ReadException("В системе нет отеля с идентификатором: " + id);
		return result;
	}

}
