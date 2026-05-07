package Main;

import java.util.Objects;

enum LengthUnit {
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}

class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 0.000001;

    public QuantityLength(double value, LengthUnit unit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return value * unit.getConversionFactor();
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double convertedValue = toBaseUnit() / targetUnit.getConversionFactor();

        return new QuantityLength(convertedValue, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double baseValue = value * source.getConversionFactor();

        return baseValue / target.getConversionFactor();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof QuantityLength)) {
            return false;
        }

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}

public class Usecase5 {

    public static void demonstrateLengthConversion(double value,
                                                   LengthUnit from,
                                                   LengthUnit to) {

        double result = QuantityLength.convert(value, from, to);

        System.out.println(value + " " + from + " = " + result + " " + to);
    }

    public static void demonstrateLengthConversion(QuantityLength quantity,
                                                   LengthUnit targetUnit) {

        QuantityLength converted = quantity.convertTo(targetUnit);

        System.out.println(quantity + " = " + converted);
    }

    public static void demonstrateLengthEquality(QuantityLength q1,
                                                 QuantityLength q2) {

        System.out.println(q1 + " equals " + q2 + " -> " + q1.equals(q2));
    }

    public static void main(String[] args) {

        System.out.println("=== UC5 Quantity Measurement ===");

        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);

        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);

        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);

        demonstrateLengthConversion(2.54,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES);

        QuantityLength yard = new QuantityLength(1.0, LengthUnit.YARDS);

        demonstrateLengthConversion(yard, LengthUnit.INCHES);

        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCHES);

        demonstrateLengthEquality(feet, inches);
    }
}