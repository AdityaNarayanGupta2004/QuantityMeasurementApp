package quantitymeasurement;
import quantitymeasurement.model.Length;
import quantitymeasurement.model.LengthUnit;
import quantitymeasurement.model.Weight;
import quantitymeasurement.model.WeightUnit;

public class QuantityMeasurementApp {

//     //Generic method to demonstrate Length equality
//    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
//    	System.out.println("Equal ("+length1.compare(length2)+")");
//        return length1.equals(length2);
//    }
//
//    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1,
//    		double value2, LengthUnit unit2) {
//    	Length length1 = new Length(value1, unit1);
//    	Length length2 = new Length(value2, unit2);
//    	
//    	boolean ans = demonstrateLengthEquality(length1, length2);
//    	System.out.println("Input: "+ length1 +" and "+ length2);
//    	System.out.println("Output: Equal("+ ans+" )\n");
//    	return ans;
//    }
//    
//	// Demonstrate length conversion from one unit to another
//	public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
//		Length length = new Length(value, fromUnit);
//		
//		return length.convertTo(toUnit);
//	}
//	
//	// Overloading length conversion from length object to another length object
//	public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
//		return length.convertTo(toUnit);
//	}
//	
//	public static Length demonstrateLengthAddition(Length l1, Length l2) {
//		return l1.add(l2);
//	}
//	
//	public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit target) {
//		return length1.add(length2, target);
//	}
	
	
//	Demonstrate weight equality between two weight instances
	 public static boolean demonstrateWeightEquality(Weight w1, Weight w2){
		 System.out.println("Input: " + w1 + ".equals(" + w2 + ")");
	        boolean result = w1.equals(w2);
	        System.out.println("Output: " + result + "\n");
	        return result;
	 }
	 
//	 comparison of weight between two weights specified by value and unit
	 public static boolean demonstrateWeightComparison(double value1, WeightUnit unit1, double value2, WeightUnit unit2){
		 return new Weight(value1, unit1).equals(new Weight(value2, unit2));
	 } 
	 
//	 conversion of weight from one unit to another
	 public static Weight demonstrateWeightConversion(double value, WeightUnit fromUnit, WeightUnit toUnit){
		 Weight weight = new Weight(value, fromUnit).convertTo(toUnit);
		 return weight;
	 }
//	 conversion of weight from one weight instance to another unit
	 public static Weight demonstrateWeightConversion(Weight weight, WeightUnit toUnit){
		 System.out.println("Input: " + weight + ".convertTo(" + toUnit + ")");
	        Weight converted = weight.convertTo(toUnit);
	        System.out.println("Output: " + converted + "\n");
	        return converted;
	 }
	 
	 // addition of second weigth to first weight
	 public static Weight demonstrateWeightAddition(Weight w1, Weight w2){
		 System.out.println("Input: add(" + w1 + ", " + w2 + ")");
	        Weight sum = w1.add(w2);
	        System.out.println("Output: " + sum + "\n");
	        return sum;
	 }
	
//	 addition of second weight to first weight with target unit
	 public static Weight demonstrateWeightAddition(Weight w1, Weight w2, WeightUnit targetUnit){
		 System.out.println("Input: add(" + w1 + ", " + w2 + ", " + targetUnit + ")");
	        Weight sum = w1.add(w2, targetUnit);
	        System.out.println("Output: " + sum + "\n");
	        return sum;
	 }
    
    // main Method
    public static void main(String[] args) {
    	Weight w1 = new Weight(1, WeightUnit.KILOGRAM);
    	
    	Weight w2 = new Weight(1, WeightUnit.KILOGRAM);
    	Weight w3 = new Weight(1000, WeightUnit.GRAM);
    	Weight w4 = new Weight(2, WeightUnit.POUND);
    	Weight w5 = new Weight(0.5, WeightUnit.KILOGRAM);
    	Weight w6 = new Weight(453.592 ,WeightUnit.GRAM);
    	Weight w7 = new Weight(2.20462, WeightUnit.POUND);
    	
    	System.out.println("Equality Comparision");
    	demonstrateWeightEquality(w1, w2);
        demonstrateWeightEquality(w1, w3);
        
        System.out.println("Unit Conversion");
        demonstrateWeightConversion(w1, WeightUnit.GRAM);
        demonstrateWeightConversion(w7, WeightUnit.KILOGRAM);
        demonstrateWeightConversion(new Weight(0.0, WeightUnit.KILOGRAM), WeightUnit.GRAM);
        
        System.out.println("Addition");
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.KILOGRAM), new Weight(2.0, WeightUnit.KILOGRAM));
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.KILOGRAM), new Weight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);
        demonstrateWeightAddition(new Weight(1.0, WeightUnit.POUND), new Weight(453.592, WeightUnit.GRAM), WeightUnit.POUND);
    	
        Weight pound = new Weight(1, WeightUnit.POUND);
		Weight gram = new Weight(453.592, WeightUnit.GRAM);
		demonstrateWeightConversion(pound, WeightUnit.GRAM);
		demonstrateWeightConversion(gram, WeightUnit.POUND);
    	
    	
    	
    	
    	
    	
    	
    	
    	
   	}

}
