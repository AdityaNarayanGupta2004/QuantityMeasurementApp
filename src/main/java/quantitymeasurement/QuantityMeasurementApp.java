package quantitymeasurement;
import quantitymeasurement.model.Length;
import quantitymeasurement.model.Length.LengthUnit;

public class QuantityMeasurementApp {

     //Generic method to demonstrate Length equality
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
    	System.out.println("Equal ("+length1.compare(length2)+")");
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1,
    		double value2, Length.LengthUnit unit2) {
    	Length length1 = new Length(value1, unit1);
    	Length length2 = new Length(value2, unit2);
    	
    	boolean ans = demonstrateLengthEquality(length1, length2);
    	System.out.println("Input: "+ length1 +" and "+ length2);
    	System.out.println("Output: Equal("+ ans+" )\n");
    	return ans;
    }
    
	// Demonstrate length conversion from one unit to another
	public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
		Length length = new Length(value, fromUnit);
		
		return length.convertTo(toUnit);
	}
	
	// Overloading length conversion from length object to another length object
	public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
		return length.convertTo(toUnit);
	}
    
    // main Method
    public static void main(String[] args) {
        // feet vs Inch
    	System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));
    	
    	//Inches vs Yards 
    	System.out.println(demonstrateLengthConversion(36.0, Length.LengthUnit.INCHES, Length.LengthUnit.YARDS));
    	
    	// Yards vs Feet
    	System.out.println(demonstrateLengthConversion(3.0, Length.LengthUnit.YARDS,Length.LengthUnit.FEET));
    	
    	// Centimeters vs Inches
    	System.out.println(demonstrateLengthConversion(1.0, Length.LengthUnit.CENTIMETERS,Length.LengthUnit.INCHES));
    
    	//Feet vs Inches
    	System.out.println(demonstrateLengthConversion(0.0,Length.LengthUnit.CENTIMETERS ,Length.LengthUnit.FEET));
    }
}
