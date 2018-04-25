package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
*@ClassName:CalculatorTest
*@Description:TODO
*@author:minshuaibo
*@date创建时间：2018年4月24日下午1:56:28
*@parameter
*@since
*@return
*/
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
        System.out.println(calculator.getResult());
	}
}
