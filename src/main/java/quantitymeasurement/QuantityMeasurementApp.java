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
	
	public static Length demonstrateLengthAddition(Length l1, Length l2) {
		return l1.add(l2);
	}
	
	public static Length demonstrateLengthAddition(Length length1, Length lenght2, LengthUnit target) {
		return length1.add(length1, target);
	}
    
    // main Method
    public static void main(String[] args) {
    	
    	// demonstrate feet to Inch addition and converted to feet
    	Length l1 = new Length(1,LengthUnit.FEET);
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	System.out.println("Input: add("+l1+", "+l2+", FEET)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l1, l2, LengthUnit.FEET));
    	
    	// demonstrate feet to Inches addition and converted to Inches
    	Length l3 = new Length(1,LengthUnit.FEET);
    	Length l4 = new Length(12, LengthUnit.INCHES);
    	System.out.println("Input: add("+l3+", "+l4+", INCHES)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l3, l4,LengthUnit.INCHES));
    	
    	// demonstrate feet to yard addition and converted to yards
    	Length l5 = new Length(1,LengthUnit.FEET);
    	Length l6 = new Length(12, LengthUnit.YARDS);
    	System.out.println("Input: add("+l5+", "+l6+", YARDS)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l5, l6,LengthUnit.YARDS));
    
    	// demonstrate Yard to feet addition and converted to yard
    	Length l7 = new Length(1,LengthUnit.YARDS);
    	Length l8 = new Length(3, LengthUnit.FEET);
    	System.out.println("Input: add("+l7+", "+l8+", YARDS)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l7, l8, LengthUnit.YARDS));
    	
    	// demonstrate Inches to yard addition and converted to feet
    	Length l9 = new Length(36,LengthUnit.INCHES);
    	Length l10 = new Length(1, LengthUnit.YARDS);
    	System.out.println("Input: add("+l9+", "+l10+", FEET)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l9, l10, LengthUnit.FEET));
    	
    	// demonstrate Centimeter to Inch addition and converted to Centimeter
    	Length l11 = new Length(2.54,LengthUnit.CENTIMETERS);
    	Length l12 = new Length(1, LengthUnit.INCHES);
    	System.out.println("Input: add("+l11+", "+l12+", CENTOMETER)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l11, l12, LengthUnit.CENTIMETERS));
    	
    	// demonstrate feet to inches addition and converted to yard
    	Length l13 = new Length(5,LengthUnit.FEET);
    	Length l14= new Length(0.0, LengthUnit.INCHES);
    	System.out.println("Input: add("+l13+", "+l14+", YARDS)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l13, l14, LengthUnit.YARDS));
    	
    	// demonstrate feet to feet addition and converted to inches
    	Length l15 = new Length(5,LengthUnit.FEET);
    	Length l16= new Length(-2, LengthUnit.FEET);
    	System.out.println("Input: add("+l15+", "+l16+", INCHES)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l15, l16, LengthUnit.INCHES));
    }
}
