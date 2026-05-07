import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.001;

    @Test
    void testEquality_KilogramToKilogram_SameValue() {

        QuantityWeight w1 =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        assertEquals(w1, w2);
    }

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        assertEquals(kilogram, gram);
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight pound =
                new QuantityWeight(2.20462,
                        WeightUnit.POUND);

        assertEquals(kilogram, pound);
    }

    @Test
    void testConversion_KilogramToGram() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                kilogram.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testConversion_PoundToKilogram() {

        QuantityWeight pound =
                new QuantityWeight(2.20462,
                        WeightUnit.POUND);

        QuantityWeight result =
                pound.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testAddition_SameUnit() {

        QuantityWeight w1 =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight w2 =
                new QuantityWeight(2.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight result =
                w1.add(w2);

        assertEquals(3.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testAddition_CrossUnit() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                kilogram.add(gram);

        assertEquals(2.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                kilogram.add(gram,
                        WeightUnit.GRAM);

        assertEquals(2000.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testEquality_NullComparison() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        assertNotEquals(kilogram, null);
    }

    @Test
    void testEquality_SameReference() {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        assertEquals(kilogram, kilogram);
    }

    @Test
    void testEquality_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0,
                        null));
    }

    @Test
    void testConversion_RoundTrip() {

        QuantityWeight kilogram =
                new QuantityWeight(1.5,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                kilogram.convertTo(WeightUnit.GRAM);

        QuantityWeight result =
                gram.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testAddition_WithZero() {

        QuantityWeight kilogram =
                new QuantityWeight(5.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight zero =
                new QuantityWeight(0.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                kilogram.add(zero);

        assertEquals(5.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {

        QuantityWeight kilogram =
                new QuantityWeight(5.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight negative =
                new QuantityWeight(-2000.0,
                        WeightUnit.GRAM);

        QuantityWeight result =
                kilogram.add(negative);

        assertEquals(3.0,
                result.getValue(),
                EPSILON);
    }
}