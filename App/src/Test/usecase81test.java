```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    void testLengthUnitEnum_FeetConstant() {
        assertEquals(1.0,
                LengthUnit.FEET.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        assertEquals(1.0 / 12,
                LengthUnit.INCHES.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        assertEquals(3.0,
                LengthUnit.YARDS.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        assertEquals(1.0 / 30.48,
                LengthUnit.CENTIMETERS.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        assertEquals(5.0,
                LengthUnit.FEET.convertToBaseUnit(5.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        assertEquals(3.0,
                LengthUnit.YARDS.convertToBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        assertEquals(1.0,
                LengthUnit.CENTIMETERS.convertToBaseUnit(30.48),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        assertEquals(2.0,
                LengthUnit.FEET.convertFromBaseUnit(2.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        assertEquals(1.0,
                LengthUnit.YARDS.convertFromBaseUnit(3.0),
                EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        assertEquals(30.48,
                LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_Equality() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(12.0,
                result.getValue(),
                EPSILON);

        assertEquals(LengthUnit.INCHES,
                result.getUnit());
    }

    @Test
    void testQuantityLengthRefactored_Add() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                feet.add(inches, LengthUnit.FEET);

        assertEquals(2.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result =
                feet.add(inches, LengthUnit.YARDS);

        assertEquals(0.6667,
                result.getValue(),
                0.01);
    }

    @Test
    void testQuantityLengthRefactored_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(Double.NaN,
                        LengthUnit.FEET));
    }

    @Test
    void testRoundTripConversion_RefactoredDesign() {

        QuantityLength feet =
                new QuantityLength(5.0, LengthUnit.FEET);

        QuantityLength inches =
                feet.convertTo(LengthUnit.INCHES);

        QuantityLength result =
                inches.convertTo(LengthUnit.FEET);

        assertEquals(5.0,
                result.getValue(),
                EPSILON);
    }

    @Test
    void testAddition_Commutativity() {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result1 =
                feet.add(inches, LengthUnit.FEET);

        QuantityLength result2 =
                inches.add(feet, LengthUnit.FEET);

        assertEquals(result1, result2);
    }

    @Test
    void testUnitImmutability() {

        assertNotNull(LengthUnit.FEET);
        assertNotNull(LengthUnit.INCHES);
        assertNotNull(LengthUnit.YARDS);
        assertNotNull(LengthUnit.CENTIMETERS);
    }
}
```
