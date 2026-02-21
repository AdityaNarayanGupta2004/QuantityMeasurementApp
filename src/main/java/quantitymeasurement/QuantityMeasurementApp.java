package quantitymeasurement;
import quantitymeasurement.model.Length;
import quantitymeasurement.model.LengthUnit;

public class QuantityMeasurementApp {

     //Generic method to demonstrate Length equality
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
    	System.out.println("Equal ("+length1.compare(length2)+")");
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1,
    		double value2, LengthUnit unit2) {
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
	
	public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit target) {
		return length1.add(length2, target);
	}
    
    // main Method
    public static void main(String[] args) {
    	
    	// demonstrate conversion from feet to inch
    	Length l1 = new Length(1,LengthUnit.FEET);
    	System.out.println("Input: "+l1+".convertTo(INCHES)");
    	System.out.println("Output: "+ demonstrateLengthConversion(l1,LengthUnit.INCHES));
    	
    	Length l2 = new Length(12, LengthUnit.INCHES);
    	System.out.println("Input: add("+l1+", "+l2+", FEET)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l1, l2, LengthUnit.FEET));

    	Length l3 = new Length(36, LengthUnit.INCHES);
    	Length l4 = new Length(1, LengthUnit.YARDS);
    	System.out.println("Input: "+l3+".equals("+l4+")");
    	System.out.println("Output: "+ l3.equals(l4));
    	
    	Length l5 = new Length(1, LengthUnit.YARDS);
    	Length l6 = new Length(3, LengthUnit.FEET);
    	System.out.println("Input: add("+l5+", "+l6+", YARDS)");
    	System.out.println("Output: "+ demonstrateLengthAddition(l5, l6, LengthUnit.YARDS));

    	Length l7 = new Length(2.54, LengthUnit.CENTIMETERS);
        System.out.println("Input: "+l7+".convertTo(INCHES)");
   	    System.out.println("Output: "+ demonstrateLengthConversion(l7, LengthUnit.INCHES));

    	Length l8 = new Length(5, LengthUnit.FEET);
        Length l9 = new Length(0, LengthUnit.INCHES);
   	    System.out.println("Input: add("+l8+", "+l9+", FEET)");
   	    System.out.println("Output: "+ demonstrateLengthAddition(l8, l9, LengthUnit.FEET));

   	    
   	    System.out.println("Input: LengthUnit.FEET.convertToBaseUnit(12.0)");
        System.out.println("Output: "+ LengthUnit.FEET.convertToBaseUnit(12.0));

   	    System.out.println("Input: LengthUnit.INCHES.convertToBaseUnit(12.0)");
   	    System.out.println("Output: "+ LengthUnit.INCHES.convertToBaseUnit(12.0));
   	}

}
