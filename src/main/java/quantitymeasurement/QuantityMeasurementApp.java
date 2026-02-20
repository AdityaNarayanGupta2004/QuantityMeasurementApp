package quantitymeasurement;
import quantitymeasurement.model.Length;

public class QuantityMeasurementApp {

     //Generic method to demonstrate Length equality
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

     //Defining a static method to Demonstrate Feet equality check
    public static void demonstrateFeetEquality() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(1.0, Length.LengthUnit.FEET);

        System.out.println("Feet Equality: Equal (" + demonstrateLengthEquality(length1, length2) + ")\n");
    }

    //Defining a static method to demonstrateInchesEquality
    public static void demonstrateInchesEquality() {
        Length length1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(1.0, Length.LengthUnit.INCHES);

        System.out.println("Inches Equality: Equal (" + demonstrateLengthEquality(length1, length2) + ")\n");
    }

    //define a static Demonstrate Feet and Inches comparison
    public static void demonstrateFeetInchesComparison() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Feet-Inches Equality: Equal (" + demonstrateLengthEquality(length1, length2) + ")");
    }

    // main Method
    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}
