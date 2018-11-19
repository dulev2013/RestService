package ru.hotel.DTO;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import ru.hotel.repository.entity.Point;
import ru.hotel.repository.entity.ServicesEnum;
import ru.hotel.repository.entity.Site;

/**
 * Класс описывает модель отеля
 * @author dulev
 *
 */
@Data()
@ToString(exclude="id")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor 
public class HotelDTO {

    public interface New {
    }

    public interface Update {
    }    
	
	/**
	 * Идентификатор отеля.
	 */
    @Null(groups = {New.class})
    @NotNull(groups = {Update.class})
	private String id;
	
	/**
	 * Имя отеля.
	 */
    @NotNull(groups= {New.class, Update.class})
	private String name;
    
	/**
	 * ID категории отеля
	 */
	private String catid;
	
	/**
	 * Координата отеля(широта/долгота)
	 */
	private Point point;

	/**
	 * Сайт отеля
	 */
	private Site site;
	/**
	 * Адрес отеля
	 */
	@NotNull(groups= {New.class, Update.class})
	private String addr;
	
	/**
	 * Путь к картинке отеля
	 */
	@Pattern(regexp = "^(\\/[-a-zA-Z0-9]{1,100})+\\.(jpg|png)$",groups= {New.class, Update.class})
	@NotNull(groups= {New.class, Update.class})
	private String img;
		
	
	/**
	 * Список услуг в отеле
	 */
	private Set<ServicesEnum> services = new HashSet<>();
	

	/**
	 * Конструктор
	 * @param name - имя отеля
	 * @param addr - адрес
	 * @param img  - путь к картинке отеля
	 */
	public HotelDTO(@NonNull String name,@NonNull String addr,@NonNull String img) {
		super();
		this.name = name;
		this.addr = addr;
		this.img = img;
	}
	
	
	
}
