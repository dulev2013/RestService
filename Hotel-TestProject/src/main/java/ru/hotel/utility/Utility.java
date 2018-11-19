package ru.hotel.utility;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.google.common.base.Preconditions;

import ru.hotel.DTO.HotelDTO;
import ru.hotel.repository.entity.Hotel;
import ru.hotel.repository.entity.Point;
import ru.hotel.repository.entity.ServicesEnum;
import ru.hotel.repository.entity.Site;

/**
 * Класс описывает сервисные методы с данными
 * @author dulev
 *
 */
// Возможно не совсем верное решение - создание подобного конвертора. Получается жесткая связь с полями сущности
// и с полями модели. Если изменится описание полей (например поле станет @NonNull) надо не забыть
// отредактировать конвертер
public class Utility {

	/**
	 * Метод конвертирует сущность в модель данных
	 * @param value
	 * 		Сущность
	 * @return
	 * 		Модель данных
	 * @throws IllegalArgumentException
	 */
	public static HotelDTO toHotelDTO(Hotel value) throws IllegalArgumentException{
		Preconditions.checkArgument(value != null, "Arg value = null");
		HotelDTO result = new HotelDTO(value.getName(), value.getAddr(), value.getImg());
		result.setCatid(value.getCatid());
		if (value.getPoint()!=null) {
			result.setPoint(new Point((Double)value.getPoint().getX(), value.getPoint().getY()));	
		}else
			result.setPoint(null);		
		if (value.getSite()!=null) {
			result.setSite(new Site(value.getSite().getLabel(), value.getSite().getUrl()));
		}else
			result.setSite(null);		
		Set<ServicesEnum> set = new HashSet<>();		
		if (value.getServices()!=null) {
			set.addAll(value.getServices());	
		}
		result.setServices(set);		
		result.setId(value.getId()); 
		return result;
	}
	
	/**
	 * Метод конвертирует модель данных в сущность
	 * @param value
	 * 		Модель данных
	 * @return
	 * 		Сущность
	 * @throws IllegalArgumentException
	 */
	public static Hotel toHotelEntity(HotelDTO value) throws IllegalArgumentException {
		Preconditions.checkArgument(value != null, "Arg value = null");
		Hotel result = new Hotel(value.getName(),value.getAddr(), value.getImg());
		result.setCatid(value.getCatid());
		if (value.getPoint()!=null) {
			result.setPoint(new Point(value.getPoint().getX(), value.getPoint().getY()));	
		}else
			result.setPoint(null);
		if (value.getSite()!=null) {
			result.setSite(new Site(value.getSite().getLabel(), value.getSite().getUrl()));
		}else
			result.setSite(null);	
		if ((value.getServices()!=null) && (value.getServices().size()!=0)) {
			result.setServices(new HashSet<>(value.getServices()));	
		}else 
			result.setServices(null);		
		result.setId(value.getId());
		return result;
	}	
	
	/**
	 * Метод копирует данные из модели в сущность
	 * @param source
	 * 		модель
	 * @param target
	 * 		сущность
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 */
	public static void copyData(HotelDTO source, Hotel target) throws IllegalArgumentException, NullPointerException{
		Preconditions.checkArgument(source != null, "source = null");
		Preconditions.checkArgument(target != null, "target = null");
		target.setAddr(source.getAddr());
		target.setCatid(source.getCatid());
		target.setImg(source.getImg());
		target.setName(source.getName());
		if (source.getPoint()!=null) target.setPoint(new Point(source.getPoint().getX(),source.getPoint().getY()));
		else target.setPoint(null);
		if (source.getServices()!=null) target.setServices(new HashSet<>(source.getServices()));
		else target.setServices(null);
		if (source.getSite()!=null) target.setSite(new Site(source.getSite().getLabel(), source.getSite().getUrl()));
		else target.setSite(null);
	}

	/**
	 * Метод создает модель отеля
	 * @return
	 * 		модель
	 */
	public static HotelDTO  instanceHotelDTO() {
		HotelDTO result = new HotelDTO("King Solomon Hotel", "155-159 Golders Green Rd, London NW11 9BX","/b3247259d5dd4989a55b326edebef7e78db5626b79cc19daf15baceabedc8552.png");
		result.setPoint(new Point(51.575276,-0.204098));
		result.setCatid("HC154963ZZ");
		result.setServices(new HashSet<>(Arrays.asList(ServicesEnum.RESTAURANT, ServicesEnum.FITNESS)));
		result.setSite(new Site("www.kingsolomonhotel.com","http://www.kingsolomonhotel.com/"));
		return result;
	}

	
	
	/**
	 * Метод создает сущность отеля
	 * @return
	 * 		сущность
	 */
	public static Hotel  instanceHotelEntity() {
		return new Hotel("King Solomon Hotel", "155-159 Golders Green Rd, London NW11 9BX","/b3247259d5dd4989a55b326edebef7e78db5626b79cc19daf15baceabedc8552.jpg");
	}

}
