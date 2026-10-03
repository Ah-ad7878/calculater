package pk.org.cas.calculater;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {

    @Test
    public void testBasicAddition() {
        assertEquals(8.0, Calculator.evaluate("5+3"), 0.0001);
    }

    @Test
    public void testMultipleOperationsWithPrecedence() {
        // 2 + 3 * 4 should be 2 + 12 = 14
        assertEquals(14.0, Calculator.evaluate("2+3*4"), 0.0001);

        // 10 + 5 * 2 - 6 / 3 should be 10 + 10 - 2 = 18
        assertEquals(18.0, Calculator.evaluate("10+5*2-6/3"), 0.0001);
    }

    @Test
    public void testDecimalNumbers() {
        assertEquals(6.0, Calculator.evaluate("2.5+3.5"), 0.0001);
    }

    @Test
    public void testUnaryMinusAtStart() {
        assertEquals(5.0, Calculator.evaluate("-5+10"), 0.0001);
    }

    @Test
    public void testTrailingOperatorStripping() {
        assertEquals(8.0, Calculator.evaluate("5+3+"), 0.0001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        Calculator.evaluate("10/0");
    }
}
