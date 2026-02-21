package quantitymeasurement;

import org.junit.jupiter.api.Test;

import quantitymeasurement.model.Length;
import quantitymeasurement.model.Length.LengthUnit;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
	
	@Test
	void testAddition_ExplicitTargetUnit_Feet() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);
		Length ans = l1.add(l2, LengthUnit.FEET);
		assertEquals(new Length(2.0, LengthUnit.FEET), ans);
	}

	@Test
	void testAddition_ExplicitTargetUnit_Inches(){
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);
		Length ans = l1.add(l2, LengthUnit.INCHES);
		assertEquals(new Length(24.0, LengthUnit.INCHES), ans);
	}
	
	@Test
	void testAddition_ExplicitTargetUnit_Yards() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);
		Length ans = l1.add(l2, LengthUnit.YARDS);
		assertEquals(new Length(0.67, LengthUnit.YARDS), ans);
	}
	
	@Test
	void testAddition_ExplicitTargetUnit_Centimeters() {
		Length l1 = new Length(1.0, LengthUnit.INCHES);
		Length l2 = new Length(1.0, LengthUnit.INCHES);
		Length ans = l1.add(l2, LengthUnit.CENTIMETERS);
		assertEquals(new Length(5.08, LengthUnit.CENTIMETERS), ans);
	}
	
	@Test
	void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
		Length l1 = new Length(2.0, LengthUnit.YARDS);
		Length l2 = new Length(3.0, LengthUnit.FEET);
		Length ans = l1.add(l2, LengthUnit.YARDS);
		assertEquals(new Length(3.0, LengthUnit.YARDS), ans);
	}
	
	@Test
	void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
		Length l1 = new Length(2.0, LengthUnit.YARDS);
		Length l2 = new Length(3.0, LengthUnit.FEET);
		Length ans = l1.add(l2, LengthUnit.FEET);
		assertEquals(new Length(9.0, LengthUnit.FEET), ans);
	}
	 
	@Test
	void testAddition_ExplicitTargetUnit_Commutativity() {
		Length l1 = new Length(1, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);
		Length ans = l1.add(l2, LengthUnit.YARDS);
		Length ans2 = l2.add(l1, LengthUnit.YARDS);
		assertEquals(ans, ans2);
	}
	
	@Test
	void testAddition_ExplicitTargetUnit_WithZero() {
		Length l1 = new Length(5, LengthUnit.FEET);
		Length l2 = new Length(0, LengthUnit.INCHES);
		Length ans = l1.add(l2, LengthUnit.YARDS);
		assertEquals(new Length(1.67, LengthUnit.YARDS), ans);
	}
	
	// negative value
	@Test
	void testAddition_ExplicitTargetUnit_NegativeValues() {
		Length l1 = new Length(5, LengthUnit.FEET);
		Length l2 = new Length(-2.0, LengthUnit.FEET);
		Length ans = l1.add(l2, LengthUnit.INCHES);
		assertEquals(new Length(36.0, LengthUnit.INCHES), ans);
	}
	
	// null 
	@Test
	void testAddition_ExplicitTargetUnit_NullTargetUnit() {
		Length l1 = new Length(1, LengthUnit.FEET);
		Length l = new Length(12, LengthUnit.INCHES);
		assertThrows(IllegalArgumentException.class, ()-> l1.add(null));
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_LargeToSmallScale(){
		Length length1 = new Length(1000, LengthUnit.FEET);
		Length length2 = new Length(500, LengthUnit.FEET);

		Length ans = length1.add(length2, LengthUnit.INCHES);
		assertEquals(new Length(18000, LengthUnit.INCHES), ans);
	}
	
	@Test
	public void testAddition_ExplicitTargetUnit_SmallToLargeScale(){
		Length length1 = new Length(12, LengthUnit.INCHES);
		Length length2 = new Length(12, LengthUnit.INCHES);

		Length ans = length1.add(length2, LengthUnit.YARDS);
		assertEquals(new Length(0.67, LengthUnit.YARDS), ans);
	}
	
	
}
