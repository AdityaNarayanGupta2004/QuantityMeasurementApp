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
	 return Double.compare(unit.convertToBaseUnit(value), other.unit.convertToBaseUnit(other.value)) == 0;
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
	
	public Quantity<U> add(Quantity<U> other) {
        return addAndConvert(other, unit);
	}
	
	public Quantity<U> add(Quantity<U> other, U targetUnit){
		return addAndConvert(other, targetUnit);
	}
	
	private Quantity<U> addAndConvert(Quantity<U> other, U targetUnit){
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
	
	@Override
	public String toString() {
	    return "Quantity(" + round(value) + ", " + unit.getUnitName() + ")";
	}

	public static void main(String[] args) {
		// Example Usage
		 Quantity<LengthUnit> lengthInFeet = new Quantity<>(10.0, LengthUnit.FEET);
	     Quantity<LengthUnit> lengthInInches = new Quantity<>(120.0, LengthUnit.INCHES);
	     boolean isEqual = lengthInFeet.equals(lengthInInches); // true
	     System.out.println("Are lengths equal? " + isEqual);

      // Example usage for WeightUnit
	     Quantity<WeightUnit> weightInKilograms = new Quantity<>(1.0, WeightUnit.KILOGRAM);
	     Quantity<WeightUnit> weightInGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
	     isEqual = weightInKilograms.equals(weightInGrams); // true
	     System.out.println("Are weights equal? " + isEqual);

	        // Example Conversion
	     double convertedLength = (lengthInFeet.convertTo(LengthUnit.INCHES)).getValue();
	     System.out.println("10 feet in inches: " + convertedLength);

	        // Example Addition
	     Quantity<LengthUnit> totalLength = lengthInFeet.add(lengthInInches, LengthUnit.FEET);
	     System.out.println("Total Length in feet: " + totalLength.getValue() + " " + totalLength.getUnit());

	        // Example Addition for WeightUnit
	     Quantity<WeightUnit> weightInPounds = new Quantity<>(2.0, WeightUnit.POUND);
	     Quantity<WeightUnit> totalWeight = weightInKilograms.add(weightInPounds, WeightUnit.KILOGRAM);
	     System.out.println("Total Weight in kilograms: " + totalWeight.getValue() + " " + totalWeight.getUnit());
		
	}
	
}
