package test;

import main.QuantityMeasurementApp.Feet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_SameValue() {

        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        assertTrue(first.equals(second));
    }

    @Test
    void testEquality_DifferentValue() {

        Feet first = new Feet(1.0);
        Feet second = new Feet(2.0);

        assertFalse(first.equals(second));
    }

    @Test
    void testEquality_NullComparison() {

        Feet first = new Feet(1.0);

        assertFalse(first.equals(null));
    }

    @Test
    void testEquality_NonNumericInput() {

        Feet first = new Feet(1.0);

        assertFalse(first.equals("Not a Feet Object"));
    }

    @Test
    void testEquality_SameReference() {

        Feet first = new Feet(1.0);

        assertTrue(first.equals(first));
    }
}
