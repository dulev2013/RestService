package ru.hotel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ru.hotel.repository.IHotelRepository;
import ru.hotel.repository.entity.Hotel;
import ru.hotel.utility.StringSequenceIdentifier;
import ru.hotel.utility.Utility;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.persistence.PersistenceException;

/**
 * Класс тестирует валидацию сущностей
 * @author dulev
 *
 */
//Проверка валидации частичная. Для примера 
@RunWith(SpringRunner.class)
@DataJpaTest
public class JPATests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private IHotelRepository repo;
	
	private StringSequenceIdentifier keyGen = new StringSequenceIdentifier();
	
	@Test
	public void тест_добавления_нового_отеля(){
		Hotel entity = Utility.instanceHotelEntity(); 
		entity.setId(keyGen.generateVarLength(12));
		this.entityManager.persist(entity);
		assertThat(repo.count(), equalTo((long)1));
		repo.deleteAll();
	}
	
	@Test(expected = NullPointerException.class)	
	public void тест_редактирования_имени_отеля_1(){
		new Hotel(null, null, null);
	}
	
	
	@Test(expected = PersistenceException.class)
	public void тест_редактирования_имени_отеля_2(){
		Hotel entity = Utility.instanceHotelEntity();
		entity.setName(keyGen.generateFixLength(260));
		this.entityManager.persist(entity);
	}

}
