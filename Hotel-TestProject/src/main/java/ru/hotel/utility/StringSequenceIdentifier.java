package ru.hotel.utility;

import java.io.Serializable;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * Класс предоставляет возможность генерировать случайную строку длинной до 12 символов
 * @author dulev
 *
 */
// Возможно воспользоваться последовательностью hibernat SEQUENCE и создать генератор, 
// используя коды символов.
// Когда все символы использованы, увеличиваем размер строки (max - 12) 
// Вероятно код будет генерироваться быстрее и исчезнет возможность создания дубликата
public class StringSequenceIdentifier implements IdentifierGenerator {
	public static final String generatorName = "stringGenerator";
	
	private Random rnd = new Random();
	
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
    	return generateFixLength(12);
    }
        
    /**
     * Метод возвращает произвольную строку (цифры и латиница) фиксированной длины
     * @param length
     * 		длина строки
     * @return
     * 		произвольная строка
     */
    public String generateFixLength(int length) {
    	return RandomStringUtils.random(length, true, true).toUpperCase();
    }
    
    /**
     * Метод возвращает произвольную строку переменной длины. От 0 до заданного значения 
     * @param maxLength
     * 		Максимальная длина строки
     * @return
     * 		произвольная строка
     */
    public String generateVarLength(int maxLength) {
    	int count = rnd.nextInt(maxLength)+1;
    	return this.generateFixLength(count);
    }
}
