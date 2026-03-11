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
		 double baseResult = performBaseArithmetic(other, targetUnit, ArithmeticOperation.ADD, true);
	        return buildQuantityFromBase(baseResult, targetUnit);

    }
	
	// Subtracts this quantity from another quantity of the same unit type and return the result in the unit of this quantity
	public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
	}
	
	// Subtracts this quantity from another quantity of the same unit type and return the result in the specified target unit
	public Quantity<U> subtract(Quantity<U> other, U targetUnit){
		double baseResult = performBaseArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT, true);
        return buildQuantityFromBase(baseResult, targetUnit);
    }
	
	
	//dividing this quantity by another quantity of the same unit type and returning the result as double
	public double divide(Quantity<U> other){
		return performBaseArithmetic(other, null, ArithmeticOperation.DIVIDE, false);

    }
	
	// Centralized core logic
    private double performBaseArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operation, boolean targetUnitRequired){
        validateArithmeticOperands(other, targetUnit, targetUnitRequired);

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return operation.compute(thisBase, otherBase);
    }
    
    private void validateArithmeticOperands(Quantity<U> quantity, U targetUnit, boolean targetUnitRequired){
        if (quantity == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }

        if (!Double.isFinite(this.value) || !Double.isFinite(quantity.value)) {
            throw new IllegalArgumentException("Values must be finite");
        }

        if (!this.unit.getClass().equals(quantity.unit.getClass())) {
            throw new IllegalArgumentException("Cross category operation not allowed");
        }

        if (targetUnitRequired) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            if (!this.unit.getClass().equals(targetUnit.getClass())) {
                throw new IllegalArgumentException("Invalid target unit category");
            }
        }
    }
    
    // Helper Builder
    private Quantity<U> buildQuantityFromBase(double baseValue, U targetUnit){
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Quantity<>(round(convertedValue), targetUnit);
    }
    
	@Override
	public String toString() {
	    return "Quantity(" + round(value) + ", " + unit.getUnitName() + ")";
	}
	
}
