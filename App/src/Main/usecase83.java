public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength yards =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength centimeters =
                new QuantityLength(2.54, LengthUnit.CENTIMETERS);

        System.out.println(feet.equals(inches));

        System.out.println(feet.convertTo(LengthUnit.INCHES));

        System.out.println(yards.add(feet, LengthUnit.YARDS));

        System.out.println(
                centimeters.convertTo(LengthUnit.INCHES)
        );
    }
}