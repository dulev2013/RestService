package ru.hotel.controllers;

/**
 * Класс содержит свойства для подключения к вебсервису
 * @author dulev
 *
 */
public class PropertiesHotelServerUri {

	/**
	 * Mapping главного контроллера /hotel
	 */
	public final static String mappingBase = "/hotel";
	
	/**
	 * Mapping метода добавления /add
	 */
	public final static String add = "/add";

	/**
	 * Mapping метода редактирования /update
	 */
	public final static String update = "/update";
	
	/**
	 * Mapping метода поиcка /findById
	 */
	public final static String findById = "/findById";
		
	/**
	 * Mapping метода удаления /delete
	 */
	public final static String delete = "/delete";
	
}
