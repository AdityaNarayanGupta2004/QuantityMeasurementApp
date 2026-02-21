package quantitymeasurement;

import org.junit.jupiter.api.Test;

import quantitymeasurement.model.Length;
import quantitymeasurement.model.LengthUnit;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
	
	@Test
	public void testLengthUnitEnum_FeetConstant(){
		assertTrue(LengthUnit.FEET.getConversionFactor() == 12);
	}
    
	@Test
	public void testConvertToBaseUnit_InchesToFeet(){
		assertTrue(LengthUnit.INCHES.convertToBaseUnit(12.0) == 12);
	}

	@Test
	public void testConvertFromBaseUnit_FeetToYards(){
		assertTrue(LengthUnit.YARDS.convertFromBaseUnit(36.0) == 1);
	}

	@Test
	public void testQuantityLengthRefactored_Equality(){
		Length l1 = new Length(1.0,LengthUnit.FEET);
		Length l2 = new Length(12.0,LengthUnit.INCHES);

		assertEquals(l1, l2);
	}

	@Test
	public void testQuantityLengthRefactored_ConvertTo(){
		Length l1 = new Length(1.0, LengthUnit.FEET);

		Length ans = new Length(12.0, LengthUnit.INCHES);
		assertEquals(ans, l1.convertTo(LengthUnit.INCHES));
	}

	@Test
	public void testRoundTripConversion(){
		Length l = new Length(1.0, LengthUnit.FEET);
		Length converted = l.convertTo(LengthUnit.INCHES).convertTo(LengthUnit.FEET);
		assertEquals(l, converted);
	}
	
	@Test
	public void testQuantityLengthRefactored_Add(){
		Length l1 = new Length(1.0, LengthUnit.FEET);
		Length l2 = new Length(12.0, LengthUnit.INCHES);
		
		Length ans = new Length(2.0, LengthUnit.FEET);
		assertEquals(ans,l1.add(l2, LengthUnit.FEET));
	}

	
}
