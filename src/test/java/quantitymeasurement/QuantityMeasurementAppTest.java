package quantitymeasurement;

import org.junit.jupiter.api.Test;

import quantitymeasurement.model.Length;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
	
	@Test
	void testEquality_YardToYard_SameValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length l2 = new Length(1.0, Length.LengthUnit.YARDS);
		assertTrue(l1.equals(l2));
	}
	
	@Test
	void testEquality_YardToYard_DifferentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length l2 = new Length(2.0, Length.LengthUnit.YARDS);
		assertFalse(l1.equals(l2));
	}
	
	@Test
	void testEquality_YardToFeet_EquivalentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length l2 = new Length(3.0, Length.LengthUnit.FEET);
		assertTrue(l1.equals(l2));
	}
	
	@Test
	void testEquality_FeetToYard_EquivalentValue() {
		Length l1 = new Length(3.0, Length.LengthUnit.FEET);
		Length l2 = new Length(1.0, Length.LengthUnit.YARDS);
		assertTrue(l1.equals(l2));
	}
	
	@Test
	void testEquality_YardToInches_EquivalentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length l2 = new Length(36.0, Length.LengthUnit.INCHES);
		assertTrue(l1.equals(l2));
	}
	
	@Test
	void testEquality_InchesToYard_EquivalentValue() {
		Length l1 = new Length(36.0, Length.LengthUnit.INCHES);
		Length l2 = new Length(1.0, Length.LengthUnit.YARDS);
		assertTrue(l1.equals(l2));
	}
	
	@Test
	void testEquality_YardToFeet_NonEquivalentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
		Length l2 = new Length(2.0, Length.LengthUnit.FEET);
		assertFalse(l1.equals(l2));
	}
	@Test
	void testEquality_centimetersToInches_EquivalentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
		Length l2 = new Length(0.393701, Length.LengthUnit.INCHES);
		assertTrue(l1.equals(l2));
	}
	@Test
	void testEquality_centimetersToFeet_NonEquivalentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
		Length l2 = new Length(0.393701, Length.LengthUnit.FEET);
		assertFalse(l1.equals(l2));
	}
	
	@Test
	void testEquality_MultiUnit_TransitiveProperty() {
		Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        Length inches = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
	}
	
	@Test
	void testEquality_YardWithNullUnit() {
		    assertThrows(IllegalArgumentException.class,
		            () -> new Length(1.0, null));
	}
	
	@Test
	void testEquality_YardSameReference() {
	    Length yard = new Length(2.0, Length.LengthUnit.YARDS);
	    assertTrue(yard.equals(yard));
	}
	
	@Test
	void testEquality_YardNullComparison() {
	    Length yard = new Length(2.0, Length.LengthUnit.YARDS);
	    assertFalse(yard.equals(null));
	}
	
	@Test
	void testEquality_CentimetersWithNullUnit() {
	    assertThrows(IllegalArgumentException.class,
	            () -> new Length(5.0, null));
	}
	
	@Test
	void testEquality_CentimetersSameReference() {
	    Length cm = new Length(10.0, Length.LengthUnit.CENTIMETERS);
	    assertTrue(cm.equals(cm));
	}
	
	@Test
	void testEquality_CentimetersNullComparison() {
	    Length cm = new Length(10.0, Length.LengthUnit.CENTIMETERS);
	    assertFalse(cm.equals(null));
	}
	
	@Test
	void testEquality_AllUnits_ComplexScenario() {
	    Length yards = new Length(2.0, Length.LengthUnit.YARDS);
	    Length feet = new Length(6.0, Length.LengthUnit.FEET);
	    Length inches = new Length(72.0, Length.LengthUnit.INCHES);

	    assertTrue(yards.equals(feet));
	    assertTrue(feet.equals(inches));
	    assertTrue(yards.equals(inches));
	}
}
