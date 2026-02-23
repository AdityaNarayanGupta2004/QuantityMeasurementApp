package quantitymeasurement.model;

import java.util.Objects;

public class Weight {
	
	private double value;
	private WeightUnit unit;
	
	public Weight(double value, WeightUnit unit) {
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
	
	public WeightUnit getUnit() {
		return unit;
	}
	
	private boolean compare(Weight weight) {
        return Double.compare(convertToBaseUnit(), weight.convertToBaseUnit()) == 0;
    }
	
	public boolean equals(Object obj) {
		 if (this == obj) {
	            return true;
	     }
	     if(obj==null ||this.getClass() != obj.getClass()) {
	            return false;
	        }

	        Weight weight = (Weight) obj;
	        return compare(weight);
	}
	
	public Weight convertTo(WeightUnit targetUnit) {
		if(targetUnit==null){
            throw new IllegalArgumentException("Unit cannot be null");
        }

        double baseUnitValue = convertToBaseUnit();
        double targetUnitValue = convertFromBaseToTargetUnit(baseUnitValue, targetUnit);
        return new Weight(targetUnitValue, targetUnit);
	}
	
	public Weight add(Weight thatWeight) {
		return addAndConvert(thatWeight, unit);
	}
	
	public Weight add(Weight weight, WeightUnit targetUnit) {
	    	return addAndConvert(weight, targetUnit);
	    }
	    
	    // adds the weight value and returns it its target unit
	 public Weight addAndConvert(Weight weight, WeightUnit targetUnit) {
	    	
	    	if(weight == null || targetUnit == null) {
	    		throw new IllegalArgumentException("weight and targetUnit cannit be null");
	    	}
	    	// converting the both weight to base Unit
	    	Weight thisToGram = this.convertTo(WeightUnit.GRAM);
	    	Weight thatToGram = weight.convertTo(WeightUnit.GRAM);
	    	
	    	double totalsum = thisToGram.getValue()+thatToGram.getValue();
	    	Weight totalWeight = new Weight(totalsum, WeightUnit.GRAM);
	    	
	    	return totalWeight.convertTo(targetUnit);
	    }
	 
	 private double round(double value) {
	    	return Math.round(value*100.0)/100.0;
	 }
	
	private double convertToBaseUnit(){
		return unit.convertToBaseUnit(value);
	}
	
	@Override
    public int hashCode(){
        return Objects.hash(convertToBaseUnit());
    }
	
	private double convertFromBaseToTargetUnit(double WeightInGrams, WeightUnit targetUnit){
		return targetUnit.convertFromBaseUnit(WeightInGrams);
	}
	
	@Override
    public String toString() {
        return "Quantity(" + value + ", \"" + unit.name().toLowerCase() + "\")";
    }
}
