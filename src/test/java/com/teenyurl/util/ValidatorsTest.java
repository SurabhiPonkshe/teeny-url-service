package com.teenyurl.util;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidatorsTest {
	
	@Test
	public void isValidUrlTest() {
		boolean isValid = Validators.isValidUrl("www.abc.com");
		assertEquals(isValid,true);
	}
}
