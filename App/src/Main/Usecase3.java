package main;

public class QuantityMeasurementApp {

    // Enum for supported length units
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Generic Quantity Length Class
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

        // Convert to base unit (feet)
        private double toFeet() {
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

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    public static void main(String[] args) {

        QuantityLength feetValue =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inchValue =
                new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength inchToInch1 =
                new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength inchToInch2 =
                new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println(
                "Input: Quantity(1.0, FEET) and Quantity(12.0, INCH)"
        );

        System.out.println(
                "Output: Equal (" + feetValue.equals(inchValue) + ")"
        );

        System.out.println();

        System.out.println(
                "Input: Quantity(1.0, INCH) and Quantity(1.0, INCH)"
        );

        System.out.println(
                "Output: Equal (" +
                        inchToInch1.equals(inchToInch2) + ")"
        );
    }
}