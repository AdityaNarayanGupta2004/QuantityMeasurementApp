package quantitymeasurement.model;

public enum VolumeUnit implements IMeasurable{
	
	LITRE(1.0), // base Unit
	MILLILITRE(0.001), // 1l = 1000ml
	GALLON(3.78541); // 1L = 3.78541 gallon
	
	private final double conversionFactor;

	VolumeUnit(double conversionFactor) {
		this.conversionFactor = conversionFactor;
	}
	
	 @Override
	public double getConversionFactor() {
        return conversionFactor;
    }
	
	// converting to base unit litre
	 @Override
	public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }
    
	 @Override
    public double convertFromBaseUnit(double baseValue) {
    	return baseValue/conversionFactor;
    }
    
    @Override
    public String getUnitName() {
    	return this.name();
    }
}
