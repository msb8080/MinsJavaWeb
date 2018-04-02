package com.junit.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 *@ClassName:CalculatorTest
 *@Description:TODO
 *@author:minshuaibo
 *@date创建时间：2018年3月21日下午2:21:02
 *@parameter
 *@since
 *@return
 */
@RunWith(JUnitPlatform.class)
@DisplayName("Testing using JUnit 5")
class CalculatorTest {
	private static Calculator calculator = new Calculator();
	@BeforeEach
	void setUp() throws Exception {
		calculator.clear();
	}

	@Test
	void testAdd() {
		calculator.add(3);
		calculator.add(4);
		assertEquals(7, calculator.getResult());
	}

	@Test
	void testSubstract() {
		calculator.add(8);
		calculator.substract(3);
		assertEquals(5, calculator.getResult());	}

	@Disabled
	@Test
	void testMultiply() {
		fail("Not yet implemented");
	}

	@Test
	void testDivide() {
		calculator.add(8);
        calculator.divide(2);
        assertEquals(4, calculator.getResult());
	}

}
