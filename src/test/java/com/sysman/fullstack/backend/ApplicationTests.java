package com.sysman.fullstack.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class ApplicationTests {

	void contextLoads() {
		//Intencional
	}

	void testMainMethod2() {
		assertDoesNotThrow(() -> Application.main(new String[]{}));
	}

}
