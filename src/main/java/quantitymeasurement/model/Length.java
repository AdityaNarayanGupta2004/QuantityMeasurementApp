package quantitymeasurement.model;

import java.util.Objects;

public class Length {

    // Instance variables
    private double value;
    private LengthUnit unit;


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

    public double getValue() {
    	return value;
    }

    public LengthUnit getUnit() {
    	return unit;
    }
    
    // rounding 2 decimal
    private double round(double value) {
    	return Math.round(value*100.0)/100.0;
    }
    
    // compare converted base values
    public boolean compare(Length thatLength) {
        if (thatLength == null) return false;

        return Double.compare(this.unit.convertToBaseUnit(this.value), thatLength.unit.convertToBaseUnit(thatLength.value)) == 0;
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
        return Objects.hash(this.unit.convertToBaseUnit(this.value));
    }
    
    // Conversion Method
    public Length convertTo(LengthUnit targetUnit) {
    	if(targetUnit == null) {
    		throw new IllegalArgumentException("Target unit cannot be null");
    	}
    	// converrt current value to base value
    	double baseValue = this.unit.convertToBaseUnit(this.value);
    	
    	// convert base value to target unit
    	double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
    	// rounding to 2 Decimal
    	convertedValue = round(convertedValue);
    	
    	return new Length(convertedValue, targetUnit);
    }
    
    // Adds the given length and return the sum in the unit of this instance
    public Length add(Length thatLength) {
    	return addAndConvert(thatLength, this.unit);
    }
    
    // overload the add method to convert result into a specific target
    public Length add(Length length, LengthUnit targetUnit) {
    	return addAndConvert(length, targetUnit);
    }
    
    // adds the lrngth value and returns it its target unit
    public Length addAndConvert(Length length, LengthUnit targetUnit) {
    	
    	if(length == null || targetUnit == null) {
    		throw new IllegalArgumentException("Length and targetUnit cannit be null");
    	}
    	// converting the both length to base Unit
    	Length thisToBase = this.convertTo(LengthUnit.INCHES);
    	Length thatToBase = length.convertTo(LengthUnit.INCHES);
    	
    	// adding the 
    	double sum = thisToBase.getValue() + thatToBase.getValue();
    	Length totalLength = new Length(sum, LengthUnit.INCHES);
    	
    	return totalLength.convertTo(targetUnit);
    }
    
	private double convertToBaseUnit(){
		return unit.convertToBaseUnit(value);
	}
	
	private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit){
		return targetUnit.convertFromBaseUnit(lengthInInches);
	}

    @Override
    public String toString() {
        return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
    }
}
