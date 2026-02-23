package quantitymeasurement.model;

public enum WeightUnit {

	MILLIGRAM(0.001),
	GRAM(1.0),
	KILOGRAM(1000.0),
	POUND(453.592),
	TONNE(1000000.0);
	
	private final double conversionFactor;
	
	WeightUnit(double conversionFactor){
		this.conversionFactor = conversionFactor;
	}
	
	public double getConversionFactor() {
        return conversionFactor;
    }
	
	public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }
    
    public double convertFromBaseUnit(double baseValue) {
    	return Math.round((baseValue/conversionFactor)*100.0)/100.0;
    }
    
//    public static void main(String[] args) {
//		double kilogram = 10.0;
//		double grams = WeightUnit.KILOGRAM.convertToBaseUnit(kilogram);
//		System.out.println(kilogram+ " kilogram is "+ grams+" grams.");
//		
//		double milligrams = WeightUnit.MILLIGRAM.convertFromBaseUnit(grams);
//		System.out.println(grams+" grams is "+ milligrams+" milligrams.");
//	}
	
}
