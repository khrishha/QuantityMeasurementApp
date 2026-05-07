package main;

public class QuantityMeasurementApp {

    // Enum for supported units
    public enum LengthUnit {

        FEET(12.0),
        INCH(1.0),
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

    // Generic Quantity Class
    public static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // Convert all units to inches
        private double toBaseUnit() {
            return value * unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(
                    this.toBaseUnit(),
                    other.toBaseUnit()
            ) == 0;
        }
    }

    public static void main(String[] args) {

        QuantityLength yardToFeet =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength yardToInch =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength inches =
                new QuantityLength(36.0, LengthUnit.INCH);

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength inch =
                new QuantityLength(0.393701, LengthUnit.INCH);

        System.out.println(
                "Input: Quantity(1.0, YARDS) and Quantity(3.0, FEET)"
        );

        System.out.println(
                "Output: Equal (" +
                        yardToFeet.equals(feet) + ")"
        );

        System.out.println();

        System.out.println(
                "Input: Quantity(1.0, YARDS) and Quantity(36.0, INCH)"
        );

        System.out.println(
                "Output: Equal (" +
                        yardToInch.equals(inches) + ")"
        );

        System.out.println();

        System.out.println(
                "Input: Quantity(1.0, CENTIMETERS) and Quantity(0.393701, INCH)"
        );

        System.out.println(
                "Output: Equal (" +
                        cm.equals(inch) + ")"
        );
    }
}