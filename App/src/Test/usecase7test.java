package test;

import main.LengthUnit;
import main.QuantityLength;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.01;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                q1.add(q2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                q1.add(q2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                q1.add(q2, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.INCHES);

        QuantityLength q2 =
                new QuantityLength(1.0, LengthUnit.INCHES);

        QuantityLength result =
                q1.add(q2, LengthUnit.CENTIMETERS);

        assertEquals(5.08, result.getValue(), EPSILON);
        assertEquals(LengthUnit.CENTIMETERS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result1 =
                q1.add(q2, LengthUnit.YARDS);

        QuantityLength result2 =
                q2.add(q1, LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {

        QuantityLength q1 =
                new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result =
                q1.add(q2, LengthUnit.YARDS);

        assertEquals(1.667, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {

        QuantityLength q1 =
                new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(-2.0, LengthUnit.FEET);

        QuantityLength result =
                q1.add(q2, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        QuantityLength q1 =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        assertThrows(
                IllegalArgumentException.class,
                () -> q1.add(q2, null)
        );
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        QuantityLength q1 =
                new QuantityLength(1000.0, LengthUnit.FEET);

        QuantityLength q2 =
                new QuantityLength(500.0, LengthUnit.FEET);

        QuantityLength result =
                q1.add(q2, LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        QuantityLength q1 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength q2 =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                q1.add(q2, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), EPSILON);
    }
}