package quantitymeasurement;

import org.junit.jupiter.api.Test;

import quantitymeasurement.model.LengthUnit;
import quantitymeasurement.model.Quantity;
import quantitymeasurement.model.WeightUnit;
import quantitymeasurement.model.IMeasurable;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
	
    @Test
    public void testIMeasurableInterface_LengthUnitImplementation() {
        LengthUnit unit = LengthUnit.FEET;
        assertEquals(12.0, unit.convertToBaseUnit(1.0 * 12 / 12));
        assertEquals(1.0, unit.convertFromBaseUnit(12.0));
        assertNotNull(unit.getUnitName());
        assertTrue(unit.getConversionFactor() > 0);
    }
	
    @Test
    public void testIMeasurableInterface_WeightUnitImplementation() {
        WeightUnit unit = WeightUnit.KILOGRAM;
        assertEquals(1000.0, unit.convertToBaseUnit(1.0));
        assertEquals(1.0, unit.convertFromBaseUnit(1000.0));
        assertNotNull(unit.getUnitName());
        assertTrue(unit.getConversionFactor() > 0);
    }

    @Test
    public void testIMeasurableInterface_ConsistentBehavior() {
        IMeasurable lengthUnit = LengthUnit.INCHES;
        IMeasurable weightUnit = WeightUnit.GRAM;

        assertTrue(lengthUnit.getConversionFactor() > 0);
        assertTrue(weightUnit.getConversionFactor() > 0);
        assertEquals(
                WeightUnit.KILOGRAM.convertToBaseUnit(1.0),
                WeightUnit.GRAM.convertToBaseUnit(1000.0), 0.01
        );
    }
    
    @Test
    public void testGenericQuantity_LengthOperations_Equality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertEquals(q1, q2);
    }

    @Test
    public void testGenericQuantity_LengthOperations_Conversion() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> converted = q.convertTo(LengthUnit.INCHES);
        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), converted);
    }
    
    @Test
    public void testGenericQuantity_WeightOperations_Equality() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertEquals(q1, q2);
    }

    @Test
    public void testGenericQuantity_WeightOperations_Conversion() {
        Quantity<WeightUnit> quan = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> converted = quan.convertTo(WeightUnit.GRAM);
        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), converted);
    }
    
    @Test
    public void testCrossCategoryPrevention_LengthVsWeight() {
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(length.equals(weight));
    }
    
    @Test
    public void testGenericQuantity_Addition_AllUnitCombinations() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sumLength = l1.add(l2, LengthUnit.FEET);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), sumLength);

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> sumWeight = w1.add(w2, WeightUnit.KILOGRAM);
        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), sumWeight);
    }
    
    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_Addition() {
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sumL = QuantityMeasurementApp.demonstrateAddition(l1, l2, LengthUnit.FEET);
        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), sumL);

        Quantity<WeightUnit> w1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> sumW = QuantityMeasurementApp.demonstrateAddition(w1, w2, WeightUnit.KILOGRAM);
        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), sumW);
    }
    @Test
    public void testGenericQuantity_LengthOperations_Addition() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> sum = q1.add(q2, LengthUnit.FEET);
        assertEquals(2.0, sum.getValue(), 0.01);
        assertEquals(LengthUnit.FEET, sum.getUnit());
    }

    @Test
    public void testGenericQuantity_WeightOperations_Addition() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> sum = q1.add(q2, WeightUnit.KILOGRAM);
        assertEquals(2.0, sum.getValue(), 0.01);
        assertEquals(WeightUnit.KILOGRAM, sum.getUnit());
    }
    
    @Test
    public void testImmutability_GenericQuantity() {
        Quantity<LengthUnit> quan = new Quantity<>(1.0, LengthUnit.FEET);
        assertEquals(1.0, quan.getValue(), 0.01);
        assertEquals(LengthUnit.FEET, quan.getUnit());
    }
    
    @Test
    public void testGenericQuantity_ConstructorValidation_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_InvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }
    
    @Test
    public void testInterfaceSegregation_MinimalContract() {
        // Verify LengthUnit and WeightUnit implement only minimal required methods
        IMeasurable lengthUnit = LengthUnit.FEET;
        IMeasurable weightUnit = WeightUnit.KILOGRAM;

        assertEquals("FEET", lengthUnit.getUnitName());
        assertEquals("KILOGRAM", weightUnit.getUnitName());

        double lengthBase = lengthUnit.convertToBaseUnit(1.0);
        double weightBase = weightUnit.convertToBaseUnit(1.0);

        assertTrue(lengthBase > 0);
        assertTrue(weightBase > 0);
    }
}
