package quantitymeasurement;

import org.junit.jupiter.api.Test;

import quantitymeasurement.model.Length;
import quantitymeasurement.model.LengthUnit;
import quantitymeasurement.model.Weight;
import quantitymeasurement.model.WeightUnit;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
	
	@Test
    public void testEquality_KilogramToKilogram_SameValue() {
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w2);
    }
	
	 @Test
	 public void testEquality_KilogramToKilogram_DifferentValue() {
		 Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
	     Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);
	     assertNotEquals(w1, w2);
	 }
	 @Test
	 public void testEquality_KilogramToGram_EquivalentValue() {
		 Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
		 Weight w2 = new Weight(1000.0, WeightUnit.GRAM);
	     assertEquals(w1, w2);
	  }

	    @Test
	 public void testEquality_GramToKilogram_EquivalentValue() {
	    	Weight w1 = new Weight(1000.0, WeightUnit.GRAM);
	    	Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);
	        assertEquals(w1, w2);
	 }

	@Test
	public void testQuantityLengthRefactored_Equality(){
		Length l1 = new Length(1.0,LengthUnit.FEET);
		Length l2 = new Length(12.0,LengthUnit.INCHES);

		assertEquals(l1, l2);
	}
	 @Test
	 public void kilogramNotEqualToPound() {
		 Weight kilogram = new Weight(1, WeightUnit.KILOGRAM);
	     Weight pound = new Weight(1, WeightUnit.POUND);

	     assertNotEquals(kilogram, pound);
	 }
	 
	@Test
	public void testQuantityLengthRefactored_ConvertTo(){
		Length l1 = new Length(1.0, LengthUnit.FEET);

		Length ans = new Length(12.0, LengthUnit.INCHES);
		assertEquals(ans, l1.convertTo(LengthUnit.INCHES));
	}
	
	 @Test
	 public void additionOfWeightsEqualsExpected() {
	     Weight weight1 = new Weight(1, WeightUnit.KILOGRAM);
	     Weight weight2 = new Weight(1000, WeightUnit.GRAM);

	     Weight result = weight1.add(weight2);

	     Weight expected = new Weight(2, WeightUnit.KILOGRAM);

	     assertEquals(expected, result);
	 }
	 
	 @Test
	 public void testEquality_LargeWeightValue() {
		 Weight w1 = new Weight(1000000.0, WeightUnit.GRAM);
		 Weight w2 = new Weight(1000.0, WeightUnit.KILOGRAM);

	     assertEquals(w1, w2);
	 }
	 
	 @Test
	  public void testConversion_SameUnit() {
	        Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
	        assertEquals(w1, w1.convertTo(WeightUnit.KILOGRAM));
	    }

	 @Test
	 public void testConversion_ZeroValue() {
		 Weight w1 = new Weight(0.0, WeightUnit.KILOGRAM);
	     Weight expected = new Weight(0.0, WeightUnit.GRAM);

	     assertEquals(expected, w1.convertTo(WeightUnit.GRAM));
	 }

	 @Test
	 public void testAddition_NegativeValues() {
		 Weight w1 = new Weight(5.0, WeightUnit.KILOGRAM);
		 Weight w2 = new Weight(-2000.0, WeightUnit.GRAM);
		 Weight expected = new Weight(3.0, WeightUnit.KILOGRAM);

	     assertEquals(expected, w1.add(w2));
	  }

	 @Test
	public void testAddition_LargeValues() {
		 Weight w1 = new Weight(1e6, WeightUnit.KILOGRAM);
		 Weight w2 = new Weight(1e6, WeightUnit.KILOGRAM);
		 Weight expected = new Weight(2e6, WeightUnit.KILOGRAM);

	     assertEquals(expected, w1.add(w2));
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
