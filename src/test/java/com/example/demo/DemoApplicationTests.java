package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.services.PersonService;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void getAgeTest() throws NoSuchMethodException, IllegalAccessException {
		var method = PersonService.class.getDeclaredMethod("getAge",LocalDate.class);
		method.setAccessible(true);

		try {
			var result = method.invoke(null, LocalDate.of(2058, 12, 13));
			Assertions.fail();
		} catch (InvocationTargetException ex) {
			Assertions.assertInstanceOf(IllegalArgumentException.class, ex.getTargetException());
		}

		try {
			var result = method.invoke(null, LocalDate.of(1103, 12, 13));
			Assertions.fail();
		} catch (InvocationTargetException ex) {
			Assertions.assertInstanceOf(IllegalArgumentException.class, ex.getTargetException());
		}

		method.setAccessible(false);
	}

}
