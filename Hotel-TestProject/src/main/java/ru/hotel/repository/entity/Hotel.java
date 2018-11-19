package ru.hotel.repository.entity;

import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


/**
 * Класс описывает отель.
 *
 * @author dulev
 */
@Data
@Entity
@Table(name = "Hotel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hotel {

	/**
	 * Идентификатор отеля.
	 */
	/*@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", unique=true)
	@Size(max = 12)*/
    /*@Id
    @GenericGenerator(
        name = StringSequenceIdentifier.generatorName,
        strategy = "ru.hotel.repository.entity.StringSequenceIdentifier"
    )
    @GeneratedValue(
        generator = StringSequenceIdentifier.generatorName)*/
	@Id
	@Column(name = "id", unique=true, length=12)
	private String id;

	/**
	 * Название отеля
	 */
	@Column(name = "name")
	@NotNull
	private String name;

	/**
	 * ID категории отеля
	 */
	@Column(name = "catid", length=12)
	private String catid;
	
	/**
	 * Координата отеля(широта/долгота)
	 */
	@Column(name = "point")
	@Embedded
	private Point point;

	/**
	 * Адрес отеля
	 */
	@Column(name = "addr", nullable = false)
	@NotNull	
	private String addr;	
	
	/**
	 * Путь к картинке отеля
	 */
	@Column(name = "img", nullable = false)
	@Pattern(regexp="^(\\/[-a-zA-Z0-9]{1,100})+\\.(jpg|png)$")
	@NotNull
	private String img;
	
	/**
	 * Сайт отеля
	 */
	@Valid
	@Embedded
	@Column(name = "site")
	private Site site;
	
	/**
	 * Список услуг в отеле
	 */
	@ElementCollection(targetClass=ServicesEnum.class)
	@CollectionTable(name = "Services")
	@Enumerated(EnumType.STRING) 
	@Column(name = "service")
	private Collection<ServicesEnum> services;
	
	/**
	 * Конструктор с обязательными полями для заполнения
	 * @param name - имя отеля
	 * @param addr - адрес
	 * @param img  - путь к картинке отеля
	 */
	public Hotel(@NonNull String name, @NonNull String addr, @NonNull String img) {
		super();
		this.name = name;
		this.addr = addr;
		this.img = img;
	}
	
	

}
