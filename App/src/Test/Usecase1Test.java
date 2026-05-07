package test;

import main.QuantityMeasurementApp.Feet;
import main.QuantityMeasurementApp.Inches;
import main.QuantityMeasurementApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // FEET TESTS

    @Test
    void testFeetEquality_SameValue() {

        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        assertTrue(first.equals(second));
    }

    @Test
    void testFeetEquality_DifferentValue() {

        Feet first = new Feet(1.0);
        Feet second = new Feet(2.0);

        assertFalse(first.equals(second));
    }

    @Test
    void testFeetEquality_NullComparison() {

        Feet first = new Feet(1.0);

        assertFalse(first.equals(null));
    }

    @Test
    void testFeetEquality_NonNumericInput() {

        Feet first = new Feet(1.0);

        assertFalse(first.equals("Invalid"));
    }

    @Test
    void testFeetEquality_SameReference() {

        Feet first = new Feet(1.0);

        assertTrue(first.equals(first));
    }

    // INCHES TESTS

    @Test
    void testInchesEquality_SameValue() {

        Inches first = new Inches(1.0);
        Inches second = new Inches(1.0);

        assertTrue(first.equals(second));
    }

    @Test
    void testInchesEquality_DifferentValue() {

        Inches first = new Inches(1.0);
        Inches second = new Inches(2.0);

        assertFalse(first.equals(second));
    }

    @Test
    void testInchesEquality_NullComparison() {

        Inches first = new Inches(1.0);

        assertFalse(first.equals(null));
    }

    @Test
    void testInchesEquality_NonNumericInput() {

        Inches first = new Inches(1.0);

        assertFalse(first.equals("Invalid"));
    }

    @Test
    void testInchesEquality_SameReference() {

        Inches first = new Inches(1.0);

        assertTrue(first.equals(first));
    }

    // STATIC METHOD TESTS

    @Test
    void testCompareFeetMethod() {

        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0));
    }

    @Test
    void testCompareInchesMethod() {

        assertTrue(QuantityMeasurementApp.compareInches(1.0, 1.0));
    }
}