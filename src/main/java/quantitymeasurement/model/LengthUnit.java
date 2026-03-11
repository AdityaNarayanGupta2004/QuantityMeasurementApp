package quantitymeasurement.model;

public enum LengthUnit implements IMeasurable {
    FEET(12.0), // 1feet = 12 inches
    INCHES(1.0), // Base Unit
	YARDS(36.0), // 1yd = 36 inch
	CENTIMETERS(0.393701); // 1cm = 0.393701 inches

    private final double conversionFactor;
    
//    constructor
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }
    
  //Convert value to base unit (INCHES)
    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }
    
    @Override
    public double convertFromBaseUnit(double baseValue) {
    	return Math.round((baseValue/conversionFactor)*100.0)/100.0;
    }
    
    @Override
    public String getUnitName() {
    	return this.name();
    }
    
}