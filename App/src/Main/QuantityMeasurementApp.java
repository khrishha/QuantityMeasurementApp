// QuantityMeasurementApp.java

interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}

enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(1.0 / 12.0);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}

enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(0.001);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}

enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    MILLILITRE(0.001);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}

class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;
    private static final double EPSILON = 0.01;

    private enum ArithmeticOperation {
        ADD {
            public double compute(double a, double b) {
                return a + b;
            }
        },
        SUBTRACT {
            public double compute(double a, double b) {
                return a - b;
            }
        },
        DIVIDE {
            public double compute(double a, double b) {
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
            }
        };

        public abstract double compute(double a, double b);
    }

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetRequired) {

        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException(
                    "Different measurement categories");
        }

        if (!Double.isFinite(value)
                || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }

        if (targetRequired && targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null");
        }
    }

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation) {

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return operation.compute(base1, base2);
    }

    private double roundToTwoDecimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double result =
                performBaseArithmetic(other,
                        ArithmeticOperation.ADD);

        double converted =
                targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(
                roundToTwoDecimals(converted),
                targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other,
                                U targetUnit) {

        validateArithmeticOperands(other, targetUnit, true);

        double result =
                performBaseArithmetic(other,
                        ArithmeticOperation.SUBTRACT);

        double converted =
                targetUnit.convertFromBaseUnit(result);

        return new Quantity<>(
                roundToTwoDecimals(converted),
                targetUnit);
    }

    public double divide(Quantity<U> other) {

        validateArithmeticOperands(other, null, false);

        return performBaseArithmetic(other,
                ArithmeticOperation.DIVIDE);
    }

    public Quantity<U> convertTo(U targetUnit) {

        double base =
                unit.convertToBaseUnit(value);

        double converted =
                targetUnit.convertFromBaseUnit(base);

        return new Quantity<>(
                roundToTwoDecimals(converted),
                targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Quantity<?> other)) {
            return false;
        }

        if (!unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double base1 =
                unit.convertToBaseUnit(value);

        double base2 =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", "
                + unit.getUnitName() + ")";
    }
}

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> length1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> length2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println(length1.add(length2));
        System.out.println(length1.subtract(length2));
        System.out.println(length1.divide(
                new Quantity<>(2.0, LengthUnit.FEET)));

        Quantity<WeightUnit> weight1 =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weight2 =
                new Quantity<>(5000.0, WeightUnit.GRAM);

        System.out.println(weight1.add(weight2));
        System.out.println(weight1.subtract(weight2));

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(500.0,
                        VolumeUnit.MILLILITRE);

        System.out.println(volume1.add(volume2));
        System.out.println(volume1.subtract(volume2));
    }
}