package quantitymeasurement.model;

public interface IMeasurable {
	
	// getting conversion value to base unit
	public double getConversionFactor();
	
	// converting value of this unit to base unit
	public double convertToBaseUnit(double value);
	
	// converting the value of base unit to this unit
	public double convertFromBaseUnit(double value);
	
	// Getting the unit name
	String getUnitName();
	
	public static void main(String[] args) {
		System.out.println("IMeasurable Interface");
	}
}
