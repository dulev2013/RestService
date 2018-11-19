package ru.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import ru.hotel.DTO.HotelDTO;
import ru.hotel.repository.IHotelRepository;
import ru.hotel.repository.entity.Hotel;
import ru.hotel.service.exceptions.AddException;
import ru.hotel.service.exceptions.DeleteException;
import ru.hotel.service.exceptions.UpdateException;
import ru.hotel.utility.Utility;
import ru.hotel.utility.StringSequenceIdentifier;

/**
 * Класс описывает реализацию сервиса управления отелями
 * 
 * @author dulev
 *
 */
@Service
@Transactional
public class HotelServiceImpl implements IHotelService {

	@Autowired
	private IHotelRepository repo;
	
	private StringSequenceIdentifier keyGen = new StringSequenceIdentifier();

	// Возможно воспользоваться последовательностью hibernat SEQUENCE и создать генератор, 
	// используя коды символов.
	// Когда все символы использованы, увеличиваем размер строки (max - 12) 
	// Вероятно код будет генерироваться быстрее и исчезнет возможность создания дубликата
	private String keyInstance() {		
		String result = keyGen.generateVarLength(12);
		if  (repo.existsById(result))
			result = keyInstance();
		return result;
	}
	
	@Override	
	public String add(HotelDTO value) throws IllegalArgumentException, AddException {
		Preconditions.checkArgument(value != null, "Arg value = null");
		String key = keyInstance();
		Hotel entity = Utility.toHotelEntity(value);
		entity.setId(key);
		entity = repo.save(entity);					
		return entity.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public HotelDTO findById(String id) throws IllegalArgumentException {
		HotelDTO result = null;
		Preconditions.checkArgument(id != null, "Arg id = null");
		Hotel entity = repo.findById(id).orElse(null); 
		if (entity!=null)
			result = Utility.toHotelDTO(entity);
		return result;	
	}

	@Override
	public void update(HotelDTO value) throws IllegalArgumentException, UpdateException {
		Preconditions.checkArgument(value != null, "Arg value = null");
		Preconditions.checkArgument(value.getId()!= null, "Идентификатор = null");
		Hotel entity = repo.findById(value.getId()).orElseThrow(() -> new UpdateException(
				"Невозможно отредактировать объект. В системе нет отеля с идентификатором: " + value.getId()));
		Utility.copyData(value, entity);		
		repo.save(entity);
	}

	@Override
	public void delete(String id) throws DeleteException, IllegalArgumentException {
		Preconditions.checkArgument(id!= null, "Arg id = null");
		repo.findById(id).orElseThrow(() -> (new DeleteException(
				"Невозможно удалить объект. В системе нет отеля с идентификатором: " + id)));
		repo.deleteById(id);
		
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();		
	}
	

}
