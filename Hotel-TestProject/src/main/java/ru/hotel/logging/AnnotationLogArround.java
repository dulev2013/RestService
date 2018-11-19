package ru.hotel.logging;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Аннатация ставится над методами, начало и конец выполенния которых надо
 * логгировать
 * @author dulev
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AnnotationLogArround {

	
}
