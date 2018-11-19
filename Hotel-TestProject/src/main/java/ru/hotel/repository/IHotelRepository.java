package ru.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.hotel.repository.entity.Hotel;

/**
 * Интерфейс репозитория отелей
 * @author dulev
 *
 */
public interface IHotelRepository extends JpaRepository<Hotel, String> {
	
	
}
