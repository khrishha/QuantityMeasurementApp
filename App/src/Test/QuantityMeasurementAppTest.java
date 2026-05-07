// QuantityMeasurementAppTest.java

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testAddition() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue());
    }

    @Test
    void testSubtraction() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(9.5, result.getValue());
    }

    @Test
    void testDivision() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2));
    }

    @Test
    void testWeightAddition() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0,
                        WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0,
                        WeightUnit.GRAM);

        assertEquals(2.0,
                q1.add(q2).getValue());
    }

    @Test
    void testVolumeSubtraction() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(5.0,
                        VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(500.0,
                        VolumeUnit.MILLILITRE);

        assertEquals(4.5,
                q1.subtract(q2).getValue());
    }

    @Test
    void testNullOperand() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(null));
    }

    @Test
    void testDivisionByZero() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(0.0,
                        LengthUnit.FEET);

        assertThrows(ArithmeticException.class,
                () -> q1.divide(q2));
    }

    @Test
    void testEquality() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0,
                        LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0,
                        LengthUnit.INCHES);

        assertEquals(q1, q2);
    }
}