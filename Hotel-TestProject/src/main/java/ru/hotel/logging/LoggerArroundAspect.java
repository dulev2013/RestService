/**
 * 
 */
package ru.hotel.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Аспект для реализации логгирования начала и конца выполнения метода
 * @author dulev
 * 
 */
@Service
@Aspect
public class LoggerArroundAspect {

	/**
	 * Точка среза, вокруг методов, помеченных аннотацией
	 * {@link ru.hotel.logging.AnnotationLogArround}.
	 * Выполняет логгирование входа и выхода из метода
	 * 
	 * @param joinPoint
	 *            Точка среза
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(AnnotationLogArround)")
	public Object pointCut(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
		logger.info("Начало выполнения метода: " + joinPoint.getSignature().getName());
		long startTime = System.currentTimeMillis();
		try {
			Object proceed = joinPoint.proceed();
			logger.info("Успешно выполнен метод: " + joinPoint.getSignature().getName()
					+ ". Врямя выполнения метода в милисекундах = " + (System.currentTimeMillis() - startTime));
			return proceed;
		} catch (Throwable e) {
			logger.info("Выполнение метода '" + joinPoint.getSignature().getName() + "' завершилось ошибкой: "
					+ e.getMessage() + ". Врямя выполнения метода в милисекундах = "
					+ (System.currentTimeMillis() - startTime));
			throw e;
		}
	}
}
