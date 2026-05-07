import java.util.Objects;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 0.0001;

    public QuantityLength(double value, LengthUnit unit) {
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

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityLength convertTo(LengthUnit targetUnit) {
        double baseValue = toBaseUnit();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityLength(convertedValue, targetUnit);
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double totalBaseValue =
                this.toBaseUnit() + other.toBaseUnit();

        double convertedValue =
                targetUnit.convertFromBaseUnit(totalBaseValue);

        return new QuantityLength(convertedValue, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {
        return add(other, this.unit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof QuantityLength))
            return false;

        QuantityLength other = (QuantityLength) obj;

        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}