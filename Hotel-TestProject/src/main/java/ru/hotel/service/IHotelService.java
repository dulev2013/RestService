package ru.hotel.service;

import ru.hotel.DTO.HotelDTO;
import ru.hotel.service.exceptions.AddException;
import ru.hotel.service.exceptions.DeleteException;
import ru.hotel.service.exceptions.UpdateException;

/**
 * Интерфейс сервиса отелей
 * @author dulev
 *
 */
public interface IHotelService {

	/**
	 * Метод добавляет новый отель
	 * @param value
	 * 			Модель нового отеля
	 * @return
	 * 			Идентификатор вновь созданного отеля
	 * @throws IllegalArgumentException
	 * @throws AddException
	 */
	public String add(HotelDTO value) throws IllegalArgumentException, AddException;

	/**
	 * Метод редактирует отель
	 * 
	 * @param value
	 * 			Модель отредактированного отеля
	 * @throws IllegalArgumentException
	 * @throws UpdateException
	 */
	public void update(HotelDTO value)
			throws IllegalArgumentException, UpdateException;

	/**
	 * Метод возвращает отель или null, если не найден
	 * 
	 * @param id
	 *            Идентификатор отеля
	 * @return Отель или null, если не найден
	 * @throws IllegalArgumentException
	 */
	public HotelDTO findById(String id) throws IllegalArgumentException;

	/**
	 * Метод удаляет отель
	 * 
	 * @param id
	 *            Идентификатор отеля
	 * @throws DeleteException
	 * @throws IllegalArgumentException
	 */
	public void delete(String id) throws DeleteException, IllegalArgumentException;
	
	/**
	 * Метод удаляет все отели
	 */
	public void deleteAll();
	
	
}
