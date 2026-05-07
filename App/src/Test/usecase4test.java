package test;

import main.QuantityMeasurementApp.LengthUnit;
import main.QuantityMeasurementApp.QuantityLength;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_YardToYard_SameValue() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength second =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {

        QuantityLength first =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength second =
                new QuantityLength(2.0, LengthUnit.YARDS);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_YardToFeet_EquivalentValue() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertTrue(feet.equals(yard));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength inch =
                new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(yard.equals(inch));
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {

        QuantityLength inch =
                new QuantityLength(36.0, LengthUnit.INCH);

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertTrue(inch.equals(yard));
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(yard.equals(feet));
    }

    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength inch =
                new QuantityLength(0.393701, LengthUnit.INCH);

        assertTrue(cm.equals(inch));
    }

    @Test
    void testEquality_CentimetersToFeet_NonEquivalentValue() {

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        assertFalse(cm.equals(feet));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength inch =
                new QuantityLength(36.0, LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    @Test
    void testEquality_YardWithNullUnit() {

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null)
        );

        assertEquals("Unit cannot be null", exception.getMessage());
    }

    @Test
    void testEquality_YardSameReference() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertTrue(yard.equals(yard));
    }

    @Test
    void testEquality_YardNullComparison() {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        assertFalse(yard.equals(null));
    }

    @Test
    void testEquality_CentimetersSameReference() {

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        assertTrue(cm.equals(cm));
    }

    @Test
    void testEquality_CentimetersNullComparison() {

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        assertFalse(cm.equals(null));
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {

        QuantityLength yard =
                new QuantityLength(2.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(6.0, LengthUnit.FEET);

        QuantityLength inch =
                new QuantityLength(72.0, LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }
}