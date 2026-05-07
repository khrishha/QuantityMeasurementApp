public class QuantityMeasurementApp {

    public static void main(String[] args) {

        QuantityWeight kilogram =
                new QuantityWeight(1.0,
                        WeightUnit.KILOGRAM);

        QuantityWeight gram =
                new QuantityWeight(1000.0,
                        WeightUnit.GRAM);

        QuantityWeight pound =
                new QuantityWeight(2.20462,
                        WeightUnit.POUND);

        System.out.println(kilogram.equals(gram));

        System.out.println(
                kilogram.convertTo(WeightUnit.GRAM)
        );

        System.out.println(
                pound.convertTo(WeightUnit.KILOGRAM)
        );

        System.out.println(
                kilogram.add(gram)
        );

        System.out.println(
                kilogram.add(gram,
                        WeightUnit.GRAM)
        );
    }
}