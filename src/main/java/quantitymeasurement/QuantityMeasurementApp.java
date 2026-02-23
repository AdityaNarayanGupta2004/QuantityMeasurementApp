package quantitymeasurement;
import quantitymeasurement.model.IMeasurable;
import quantitymeasurement.model.LengthUnit;
import quantitymeasurement.model.Quantity;
import quantitymeasurement.model.VolumeUnit;
import quantitymeasurement.model.WeightUnit;

public class QuantityMeasurementApp {
	
	// equality comparision between two quantities
	public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2){
		 return quantity1.equals(quantity2);
	 }
	
	// converion of quantity to target unit
	public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit){
		 return quantity.convertTo(targetUnit);
	 }
	
	// Addition of two quantities and returning result in unit of first quantity
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){
		 return quantity1.add(quantity2);
	 }
	
	//Addition of two quantities and returning result in unit of specific target unit
	public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
		 return quantity1.add(quantity2, targetUnit);
	 }
	
	// main method
	public static void main(String[] args) {
		// Demonstration equality between the two quantities
		Quantity<WeightUnit> weightInGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
		Quantity<WeightUnit> weightInKilograms = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		boolean areEqual = demonstrateEquality(weightInGrams, weightInKilograms);
		System.out.println("Are weights equal? " + areEqual);

		// Demonstration conversion between the two quantities
		Quantity<WeightUnit> convertedWeight = demonstrateConversion(weightInGrams,
		WeightUnit.KILOGRAM);
		System.out.println("Converted Weight: " + convertedWeight.getValue() + " " +
		convertedWeight.getUnit());

		// Demonstration addition of two quantities and return the result in the unit
		// of the first quantity
		Quantity<WeightUnit> weightInPounds = new Quantity<>(2.20462, WeightUnit.POUND);
		Quantity<WeightUnit> sumWeight = demonstrateAddition(weightInKilograms, weightInPounds);
		System.out.println("Sum Weight: " + sumWeight.getValue() + " " +
		sumWeight.getUnit());

		// Demonstration addition of two quantities and return the result in a specified
		// target unit
		Quantity<WeightUnit> sumWeightInGrams = demonstrateAddition(weightInKilograms,
		weightInPounds,
		WeightUnit.GRAM);
		System.out.println("Sum Weight in Grams: " + sumWeightInGrams.getValue() + " " +
		sumWeightInGrams.getUnit());
		
		System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
		
		Quantity<VolumeUnit> volume1 = new Quantity<>(1.0, VolumeUnit.LITRE);
		Quantity<VolumeUnit> volume2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
		Quantity<VolumeUnit> volume3 = new Quantity<>(1.0, VolumeUnit.GALLON);
		
		System.out.println(volume1.equals(volume2)); // expected true
		System.out.println(volume1.equals(volume3)); // expected false
		System.out.println(volume3.convertTo(VolumeUnit.LITRE)); // 3.78541L
		System.out.println(volume1.add(volume2)); // 2 L
		System.out.println(demonstrateConversion(volume1, VolumeUnit.MILLILITRE));
		System.out.println(demonstrateAddition(volume1, volume3, VolumeUnit.LITRE));
		
		
		
	}
}

