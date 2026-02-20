package quantitymeasurement.model;

import java.util.Objects;

public class Length {

    // Instance variables
    private final double value;
    private final LengthUnit unit;

    public enum LengthUnit {
        FEET(12.0), // 1feet = 12 inches
        INCHES(1.0), // Base Unit
    	YARDS(36.0), // 1yd = 36 inch
    	CENTIMETERS(0.393701); // 1cm = 0.393701 inches

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

     //Constructor
    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }


     //Convert value to base unit (INCHES)
    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }
    
    // compare converted base values
    public boolean compare(Length thatLength) {
        if (thatLength == null) return false;

        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Length)) return false;

        Length that = (Length) obj;

        return compare(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convertToBaseUnit());
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
    }
}
