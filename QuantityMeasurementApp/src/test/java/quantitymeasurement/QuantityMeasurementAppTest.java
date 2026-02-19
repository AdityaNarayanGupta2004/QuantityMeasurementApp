package quantitymeasurement;

import org.junit.jupiter.api.Test;

import quantitymeasurement.model.Length;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    @Test
    void testEquality_FeetToFeet_SameValue() {
    	Length l1 = new Length(1.0, Length.LengthUnit.FEET);
    	Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
    	Length l1 = new Length(1.0, Length.LengthUnit.INCHES);
    	Length l2 = new Length(1.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToInches_EquivalentValue() {
    	Length l1 = new Length(1.0, Length.LengthUnit.FEET);
    	Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_InchesToFeet_EquivalentValue() {
    	Length l1 = new Length(12.0, Length.LengthUnit.INCHES);
    	Length l2 = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(l1.equals(l2));
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
    	Length l1 = new Length(1.0, Length.LengthUnit.FEET);
    	Length l2 = new Length(2.0, Length.LengthUnit.FEET);
        assertFalse(l1.equals(l2));
    }

    @Test
    void testEquality_InchesToInches_DifferentValue() {
    	Length inch1 = new Length(1.0, Length.LengthUnit.INCHES);
    	Length inch2 = new Length(2.0, Length.LengthUnit.INCHES);
        assertFalse(inch1.equals(inch2));
    }

    @Test
    void testEquality_NullComparison() {
    	Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(feet.equals(null));
    }

    @Test
    void testEquality_SameReference() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertTrue(feet.equals(feet));
    }

    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    @Test
    void testEquality_DifferentType() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET)
                .equals("Invalid"));
    }
}
