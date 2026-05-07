package quantitymeasurement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testLengthEquality() {

        Quantity<LengthUnit> q1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(q1, q2);
    }

    @Test
    void testWeightEquality() {

        Quantity<WeightUnit> q1 =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> q2 =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(q1, q2);
    }

    @Test
    void testVolumeEquality_LitreToMillilitre() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(q1, q2);
    }

    @Test
    void testVolumeEquality_GallonToLitre() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        assertEquals(q1, q2);
    }

    @Test
    void testVolumeConversion_LitreToMillilitre() {

        Quantity<VolumeUnit> quantity =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> converted =
                quantity.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                converted
        );
    }

    @Test
    void testVolumeConversion_GallonToLitre() {

        Quantity<VolumeUnit> quantity =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> converted =
                quantity.convertTo(VolumeUnit.LITRE);

        assertEquals(
                new Quantity<>(3.79, VolumeUnit.LITRE),
                converted
        );
    }

    @Test
    void testVolumeAddition_LitrePlusMillilitre() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                q1.add(q2, VolumeUnit.LITRE);

        assertEquals(
                new Quantity<>(2.0, VolumeUnit.LITRE),
                result
        );
    }

    @Test
    void testVolumeAddition_GallonPlusLitre() {

        Quantity<VolumeUnit> q1 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> q2 =
                new Quantity<>(3.78541, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result =
                q1.add(q2, VolumeUnit.GALLON);

        assertEquals(
                new Quantity<>(2.0, VolumeUnit.GALLON),
                result
        );
    }

    @Test
    void testCrossCategoryComparison() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    @Test
    void testNullUnitValidation() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null)
        );
    }
}