package quantitymeasurement.model;

public class Quantity<U extends IMeasurable> {
	private double value;
	private U unit;
	
	public Quantity(double value, U unit) {
		if(unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if(Double.isNaN(value) || Double.isInfinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit = unit;
	}
	
	public double getValue() {
		return value;
	}
	
	public U getUnit() {
		return unit;
	}
	
	private double round(double value) {
    	return Math.round(value*100.0)/100.0;
 }
	public boolean equals(Object obj) {
		 if (this == obj) {
	         return true;
	     }
	     if(obj==null ||this.getClass() != obj.getClass()) {
	            return false;
	     }
	     Quantity<?> other = (Quantity<?>) obj;
	  // Ensure same unit category
	   if (!this.unit.getClass().equals(other.unit.getClass())) return false;

	// Compare using base unit
	   double thisBase = this.unit.convertToBaseUnit(this.value);
	   double otherBase = other.unit.convertToBaseUnit(other.value);

	   return Double.compare(thisBase, otherBase) == 0;
	  
//	 return Double.compare(unit.convertToBaseUnit(value), other.unit.convertToBaseUnit(other.value)) == 0;
	}
	
	
	// Converting this Quantity to specified target unit
	public Quantity<U> convertTo(U targetUnit) {
		 if (targetUnit == null) {
	            throw new IllegalArgumentException("Target unit cannot be null");
	      }
		double baseValue = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<U>(round(converted), targetUnit);
	}
	
	// Add the quantity to the another quantity of the same unit type
	public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
	}
	
	// Add this quantity to another quantity of the same unit type and return the result in the specific unit.
	public Quantity<U> add(Quantity<U> other, U targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass())) 
        	throw new IllegalArgumentException("Cannot add different measurement categories");
        if(!targetUnit.getClass().equals(unit.getClass()))
            throw new IllegalArgumentException("Target unit should belong to same class");
        
        double thisBaseValue = unit.convertToBaseUnit(value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);

        double total = targetUnit.convertFromBaseUnit(thisBaseValue + otherBaseValue);
        return new Quantity<>(total, targetUnit);

    }
	
	// Subtracts this quantity from another quantity of the same unit type and return the result in the unit of this quantity
	public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
	}
	
	// Subtracts this quantity from another quantity of the same unit type and return the result in the specified target unit
	public Quantity<U> subtract(Quantity<U> other, U targetUnit){
        if(targetUnit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass())) 
        	throw new IllegalArgumentException("Cannot add different measurement categories");
        if(!targetUnit.getClass().equals(unit.getClass()))
            throw new IllegalArgumentException("Target unit should belong to same class");
        
        double thisBaseValue = unit.convertToBaseUnit(value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);

        double total = targetUnit.convertFromBaseUnit(thisBaseValue - otherBaseValue);
        return new Quantity<>(total, targetUnit);

    }
	
	//dividing this quantity by another quantity of the same unit type and returning the result as double
	public double divide(Quantity<U> other){
        if (!this.unit.getClass().equals(other.unit.getClass())) 
        	throw new IllegalArgumentException("Cannot add different measurement categories");
        
        double thisBaseValue = unit.convertToBaseUnit(value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);
        
        if(otherBaseValue == 0) {
        	throw new ArithmeticException("Divide by zero");
        }

        return round(thisBaseValue/otherBaseValue);

    }
	
	
	@Override
	public String toString() {
	    return "Quantity(" + round(value) + ", " + unit.getUnitName() + ")";
	}

//	public static void main(String[] args) {
//		// Example Usage
//		 Quantity<LengthUnit> lengthInFeet = new Quantity<>(10.0, LengthUnit.FEET);
//	     Quantity<LengthUnit> lengthInInches = new Quantity<>(120.0, LengthUnit.INCHES);
//	     boolean isEqual = lengthInFeet.equals(lengthInInches); // true
//	     System.out.println("Are lengths equal? " + isEqual);
//
//      // Example usage for WeightUnit
//	     Quantity<WeightUnit> weightInKilograms = new Quantity<>(1.0, WeightUnit.KILOGRAM);
//	     Quantity<WeightUnit> weightInGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
//	     isEqual = weightInKilograms.equals(weightInGrams); // true
//	     System.out.println("Are weights equal? " + isEqual);
//
//	        // Example Conversion
//	     double convertedLength = (lengthInFeet.convertTo(LengthUnit.INCHES)).getValue();
//	     System.out.println("10 feet in inches: " + convertedLength);
//
//	        // Example Addition
//	     Quantity<LengthUnit> totalLength = lengthInFeet.add(lengthInInches, LengthUnit.FEET);
//	     System.out.println("Total Length in feet: " + totalLength.getValue() + " " + totalLength.getUnit());
//
//	        // Example Addition for WeightUnit
//	     Quantity<WeightUnit> weightInPounds = new Quantity<>(2.0, WeightUnit.POUND);
//	     Quantity<WeightUnit> totalWeight = weightInKilograms.add(weightInPounds, WeightUnit.KILOGRAM);
//	     System.out.println("Total Weight in kilograms: " + totalWeight.getValue() + " " + totalWeight.getUnit());
//		
//	}
	
}
