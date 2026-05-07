package test;

import main.QuantityMeasurementApp.LengthUnit;
import main.QuantityMeasurementApp.QuantityLength;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_InchToInch_SameValue() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength second =
                new QuantityLength(1.0, LengthUnit.INCH);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_FeetToInch_EquivalentValue() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inch =
                new QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(feet.equals(inch));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {

        QuantityLength inch =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(inch.equals(feet));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength second =
                new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength second =
                new QuantityLength(2.0, LengthUnit.INCH);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_NullUnit() {

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null)
        );

        assertEquals("Unit cannot be null", exception.getMessage());
    }

    @Test
    void testEquality_SameReference() {

        QuantityLength quantity =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(quantity.equals(quantity));
    }

    @Test
    void testEquality_NullComparison() {

        QuantityLength quantity =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(quantity.equals(null));
    }

    @Test
    void testEquality_NonQuantityObject() {

        QuantityLength quantity =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(quantity.equals("Invalid Object"));
    }
}