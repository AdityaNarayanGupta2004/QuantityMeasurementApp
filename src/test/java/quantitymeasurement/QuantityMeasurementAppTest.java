package quantitymeasurement;

import org.junit.jupiter.api.Test;

import quantitymeasurement.model.Length;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
	
	 @Test
	    void testConversion_FeetToInches() {
	        Length feet = new Length(1.0, Length.LengthUnit.FEET);
	        Length inches = feet.convertTo(Length.LengthUnit.INCHES);
	        assertEquals(new Length(12.0, Length.LengthUnit.INCHES), inches);
	    }

	    @Test
	    void testConversion_InchesToFeet() {
	        Length inches = new Length(24.0, Length.LengthUnit.INCHES);
	        Length feet = inches.convertTo(Length.LengthUnit.FEET);
	        assertEquals(new Length(2.0, Length.LengthUnit.FEET), feet);
	    }

	    @Test
	    void testConversion_YardsToInches() {
	        Length yards = new Length(1.0, Length.LengthUnit.YARDS);
	        Length inches = yards.convertTo(Length.LengthUnit.INCHES);
	        assertEquals(new Length(36.0, Length.LengthUnit.INCHES), inches);
	    }

	    @Test
	    void testConversion_InchesToYards() {
	        Length inches = new Length(72.0, Length.LengthUnit.INCHES);
	        Length yards = inches.convertTo(Length.LengthUnit.YARDS);
	        assertEquals(new Length(2.0, Length.LengthUnit.YARDS), yards);
	    }

	    @Test
	    void testConversion_CentimetersToInches() {
	        Length cm = new Length(2.54, Length.LengthUnit.CENTIMETERS);
	        Length inches = cm.convertTo(Length.LengthUnit.INCHES);
	        assertEquals(new Length(1.0, Length.LengthUnit.INCHES), inches);
	    }

	    @Test
	    void testConversion_FeetToYard() {
	        Length feet = new Length(6.0, Length.LengthUnit.FEET);
	        Length yards = feet.convertTo(Length.LengthUnit.YARDS);
	        assertEquals(new Length(2.0, Length.LengthUnit.YARDS), yards);
	    }

	    @Test
	    void testConversion_ZeroValue() {
	        Length feet = new Length(0.0, Length.LengthUnit.FEET);
	        Length inches = feet.convertTo(Length.LengthUnit.INCHES);
	        assertEquals(new Length(0.0, Length.LengthUnit.INCHES), inches);
	    }

	    @Test
	    void testConversion_NegativeValue() {
	        Length feet = new Length(-1.0, Length.LengthUnit.FEET);
	        Length inches = feet.convertTo(Length.LengthUnit.INCHES);
	        assertEquals(new Length(-12.0, Length.LengthUnit.INCHES), inches);
	    }

	    @Test
	    void testConversion_InvalidUnit_Throws() {
	        Length feet = new Length(1.0, Length.LengthUnit.FEET);
	        assertThrows(IllegalArgumentException.class,
	                () -> feet.convertTo(null));
	    }

	    @Test
	    void testConversion_NaNOrInfinite_Throws() {
	        assertThrows(IllegalArgumentException.class,
	                () -> new Length(Double.NaN, Length.LengthUnit.FEET));

	        assertThrows(IllegalArgumentException.class,
	                () -> new Length(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET));
	    }
}
