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
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        this.value = value;
        this.unit = unit;
    }


     //Convert value to base unit (INCHES)
    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }
    
    // rounding 2 decimal
    private double round(double value) {
    	return Math.round(value*100.0)/100.0;
    }
    
    // compare converted base values
    public boolean compare(Length thatLength) {
        if (thatLength == null) return false;

        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }
    
    public double getValue() {
    	return value;
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
    
    // Conversion Method
    public Length convertTo(LengthUnit targetUnit) {
    	if(targetUnit == null) {
    		throw new IllegalArgumentException("Target unit cannot be null");
    	}
    	// converrt current value to base value
    	double baseValue = convertToBaseUnit();
    	
    	// convert base value to target unit
    	double convertedValue = baseValue/targetUnit.getConversionFactor();
    	// rounding to 2 Decimal
    	convertedValue = round(convertedValue);
    	
    	return new Length(convertedValue, targetUnit);
    }
    
    public Length add(Length thatLength) {
    	if(thatLength == null) {
    		throw new IllegalArgumentException("Length for adding cannit be null");
    	}
    	// converting to base value(Inches)
    	Length thisToBase = this.convertTo(LengthUnit.INCHES);
    	Length thatToBase = thatLength.convertTo(LengthUnit.INCHES);
    	
    	// adding the 
    	double sum = round(thisToBase.getValue() + thatToBase.getValue());
    	Length totalLength = new Length(sum, LengthUnit.INCHES);
    	
    	return totalLength.convertTo(this.unit);
    			
    }
    
    

    @Override
    public String toString() {
        return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
    }
}
