package test;

import main.QuantityMeasurementApp.QuantityLength;
import main.QuantityMeasurementApp.LengthUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(2.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {

        QuantityLength q1 =
                new QuantityLength(6.0, LengthUnit.INCHES);

        QuantityLength q2 =
                new QuantityLength(6.0, LengthUnit.INCHES);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(12.0, LengthUnit.INCHES),
                result
        );
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(2.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {

        QuantityLength q1 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength q2 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(24.0, LengthUnit.INCHES),
                result
        );
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength q2 =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(2.0, LengthUnit.YARDS),
                result
        );
    }

    @Test
    void testAddition_WithZero() {

        QuantityLength q1 =
                new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(5.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityLength q1 =
                new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(3.0, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_NullSecondOperand() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertThrows(
                IllegalArgumentException.class,
                () -> q1.add(null)
        );
    }

    @Test
    void testAddition_LargeValues() {

        QuantityLength q1 =
                new QuantityLength(1e6, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(1e6, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(2e6, LengthUnit.FEET),
                result
        );
    }

    @Test
    void testAddition_SmallValues() {

        QuantityLength q1 =
                new QuantityLength(0.001, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(0.002, LengthUnit.FEET);

        QuantityLength result = q1.add(q2);

        assertEquals(
                new QuantityLength(0.003, LengthUnit.FEET),
                result
        );
    }
}