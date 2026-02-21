package quantitymeasurement;

import org.junit.jupiter.api.Test;

import quantitymeasurement.model.Length;
import quantitymeasurement.model.Length.LengthUnit;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
	
	// same unit addition
	@Test
	void testAddition_SameUnit_FeetPlusFeet() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(2.0, LengthUnit.FEET);
		Length ans = l1.add(l2);
		assertEquals(new Length(3.0, LengthUnit.FEET), ans);
	}
	
	// feet+feet
	@Test
	void testAddition_SameUnit_InchPlusInch(){
		Length l1 = new Length(6.0, LengthUnit.INCHES);
		Length l2 = new Length(6.0, LengthUnit.INCHES);
		Length ans = l1.add(l2);
		assertEquals(new Length(12.0, LengthUnit.INCHES), ans);
	}
	
	// feet+Inches
	@Test
	void testAddition_CrossUnit_FeetPlusInches() {
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);
		Length ans = l1.add(l2);
		assertEquals(new Length(2.0, LengthUnit.FEET), ans);
	}
	
	// inches+feet
	@Test
	void testAddition_CrossUnit_InchPlusFeet() {
		Length l1 = new Length(12.0, LengthUnit.INCHES);
		Length l2 = new Length(1.0, LengthUnit.FEET);
		Length ans = l1.add(l2);
		assertEquals(new Length(24.0, LengthUnit.INCHES), ans);
	}
	
	// yards+feet
	@Test
	void testAddition_CrossUnit_YardPlusFeet() {
		Length l1 = new Length(1.0, LengthUnit.YARDS);
		Length l2 = new Length(3.0, LengthUnit.FEET);
		Length ans = l1.add(l2);
		assertEquals(new Length(2.0, LengthUnit.YARDS), ans);
	}
	
	// CM+inch
	@Test
	void testAddition_CrossUnit_CentimeterPlusInch() {
		Length l1 = new Length(2.54, LengthUnit.CENTIMETERS);
		Length l2 = new Length(1.0, LengthUnit.INCHES);
		Length ans = l1.add(l2);
		assertEquals(new Length(5.08, LengthUnit.CENTIMETERS), ans);
	}
	 
	// commutative case a+b == b+a
	@Test
	void testAddition_Commutativity() {
		Length l1 = new Length(1, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);
		Length ans = l1.add(l2);
		Length ans2 = l2.add(l1);
		assertEquals(ans, ans2);
	}
	
	@Test
	void testAddition_WithZero() {
		Length l1 = new Length(5, LengthUnit.FEET);
		Length l2 = new Length(0, LengthUnit.INCHES);
		Length ans = l1.add(l2);
		assertEquals(new Length(5.0, LengthUnit.FEET), ans);
	}
	
	// negative value
	@Test
	void testAddition_NegativeValues() {
		Length l1 = new Length(5, LengthUnit.FEET);
		Length l2 = new Length(-2.0, LengthUnit.FEET);
		Length ans = l1.add(l2);
		assertEquals(new Length(3.0, LengthUnit.FEET), ans);
	}
	
	// null 
	@Test
	void testAddition_NullSecondOperand() {
		Length l1 = new Length(2, LengthUnit.FEET);
		assertThrows(IllegalArgumentException.class, ()-> l1.add(null));
	}
	
	// for large value
	@Test
	void testAddition_LargeValues(){
		Length l1 = new Length(1000000, LengthUnit.FEET);
		Length l2 = new Length(1000000.0, LengthUnit.FEET);
		Length ans = l1.add(l2);
		assertEquals(new Length(2000000.0, LengthUnit.FEET), ans);
	}
	
	// for small value
	@Test
	void testAddition_SmallValues() {
		double unitValue = 0.0001;
		Length l1 = new Length(0.001, LengthUnit.FEET);
		Length l2 = new Length(0.002, LengthUnit.FEET);
		Length ans = l1.add(l2);
		assertEquals(new Length(0.000, LengthUnit.FEET), ans);
	}
	
}
