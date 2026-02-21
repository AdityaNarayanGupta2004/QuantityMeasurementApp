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
    
    // main Method
    public static void main(String[] args) {
    	
    	// demonstrate feet to feet addition
    	Length l1 = new Length(1,LengthUnit.FEET);
    	Length l2 = new Length(2, LengthUnit.FEET);
    	System.out.println("Input: add("+l1+", "+l2+" )");
    	System.out.println("Output: "+ demonstrateLengthAddition(l1, l2));
    	
    	// demonstrate feet to Inches addition
    	Length l3 = new Length(1,LengthUnit.FEET);
    	Length l4 = new Length(12, LengthUnit.INCHES);
    	System.out.println("Input: add("+l3+", "+l4+" )");
    	System.out.println("Output: "+ demonstrateLengthAddition(l3, l4));
    	
    	// demonstrate Inches to feet addition
    	Length l5 = new Length(12,LengthUnit.INCHES);
    	Length l6 = new Length(1, LengthUnit.FEET);
    	System.out.println("Input: add("+l5+", "+l6+" )");
    	System.out.println("Output: "+ demonstrateLengthAddition(l5, l6));
    
    	// demonstrate Yard to feet addition
    	Length l7 = new Length(1,LengthUnit.YARDS);
    	Length l8 = new Length(3, LengthUnit.FEET);
    	System.out.println("Input: add("+l7+", "+l8+" )");
    	System.out.println("Output: "+ demonstrateLengthAddition(l7, l8));
    	
    	// demonstrate Inches to yard addition
    	Length l9 = new Length(36,LengthUnit.INCHES);
    	Length l10 = new Length(1, LengthUnit.YARDS);
    	System.out.println("Input: add("+l9+", "+l10+" )");
    	System.out.println("Output: "+ demonstrateLengthAddition(l9, l10));
    	
    	// demonstrate Centimeter to Inch addition
    	Length l11 = new Length(2.54,LengthUnit.CENTIMETERS);
    	Length l12 = new Length(1, LengthUnit.INCHES);
    	System.out.println("Input: add("+l11+", "+l12+" )");
    	System.out.println("Output: "+ demonstrateLengthAddition(l11, l12));
    	
    	// demonstrate feet to feet addition
    	Length l13 = new Length(5,LengthUnit.FEET);
    	Length l14= new Length(-2, LengthUnit.FEET);
    	System.out.println("Input: add("+l13+", "+l14+" )");
    	System.out.println("Output: "+ demonstrateLengthAddition(l13, l14));
    }
}
