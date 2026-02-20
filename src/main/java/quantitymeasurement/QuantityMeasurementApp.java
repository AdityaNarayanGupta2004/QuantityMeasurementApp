package quantitymeasurement;
import quantitymeasurement.model.Length;

public class QuantityMeasurementApp {

     //Generic method to demonstrate Length equality
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
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
    
    //define a static Demonstrate Feet and Inches comparison
    public static void demonstrateFeetInchesComparison() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Feet-Inches Equality: Equal (" + demonstrateLengthEquality(length1, length2) + ")");
    }
    
  //define a static Demonstrate Yard and Inches comparison
    public static void demonstrateYardInchesComparison() {
        Length length1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(36.0, Length.LengthUnit.INCHES);

        System.out.println("Yard-Inches Equality: Equal (" + demonstrateLengthEquality(length1, length2) + ")");
    }
    
  //define a static Demonstrate Centimeter and Inches comparison
    public static void demonstrateCentiMeterInchesComparison() {
        Length length1 = new Length(100.0, Length.LengthUnit.CENTIMETERS);
        Length length2 = new Length(39.3701, Length.LengthUnit.INCHES);

        System.out.println("Feet-Inches Equality: Equal (" + demonstrateLengthEquality(length1, length2) + ")");
    }
    
    // main Method
    public static void main(String[] args) {
    	demonstrateFeetInchesComparison();
    	demonstrateYardInchesComparison();
    	demonstrateCentiMeterInchesComparison();
        // feet vs Inch
    	demonstrateLengthComparison(1.0, Length.LengthUnit.FEET, 12, Length.LengthUnit.INCHES);
    	
    	// Yards vs Inches
    	demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 36, Length.LengthUnit.INCHES);
    	
    	// Yards vs Feet
    	demonstrateLengthComparison(3.0, Length.LengthUnit.YARDS, 1.0, Length.LengthUnit.FEET);
    	
    	// Centimeters vs Inches
    	demonstrateLengthComparison(100.0, Length.LengthUnit.CENTIMETERS, 39.3701, Length.LengthUnit.INCHES);
    
    	// Centimeter vs Feet
    	demonstrateLengthComparison(30.48,Length.LengthUnit.CENTIMETERS , 1.0, Length.LengthUnit.FEET);
    }
}
