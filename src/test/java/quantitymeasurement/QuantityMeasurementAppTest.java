package quantitymeasurement;

import org.junit.jupiter.api.Test;

import quantitymeasurement.model.LengthUnit;
import quantitymeasurement.model.Quantity;
import quantitymeasurement.model.VolumeUnit;
import quantitymeasurement.model.WeightUnit;
import quantitymeasurement.model.IMeasurable;
import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
	
	private static final double DELTA = 0.01;

    // ---------------- ADDITION ----------------

    @Test
    public void testAdd_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // ---------------- SUBTRACTION ----------------

    @Test
    public void testSubtract_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(9.5, result.getValue(), DELTA);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    // ---------------- DIVISION ----------------

    @Test
    public void testDivide_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(1.0, result, DELTA);
    }

    // ---------------- VALIDATION CONSISTENCY ----------------

    @Test
    public void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q.add(null));
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }

    // ---------------- ROUNDING ----------------

    @Test
    public void testRounding_Addition_TwoDecimalPlaces() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.3333, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(1.3333, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.add(q2);

        assertEquals(2.67, result.getValue(), DELTA);
    }

    @Test
    public void testDivision_NoRounding() {
        Quantity<LengthUnit> q1 = new Quantity<>(7.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        double result = q1.divide(q2);

        assertEquals(3.5, result, DELTA);
    }

    // ---------------- TARGET UNIT ----------------

    @Test
    public void testExplicitTargetUnit_Addition() {
        Quantity<WeightUnit> q1 = new Quantity<>(10.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(5000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2, WeightUnit.GRAM);

        assertEquals(15000.0, result.getValue(), DELTA);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    // ---------------- IMMUTABILITY ----------------

    @Test
    public void testImmutability_AfterAdd() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        q1.add(q2);

        assertEquals(1.0, q1.getValue(), DELTA);
        assertEquals(12.0, q2.getValue(), DELTA);
    }

    // ---------------- CHAIN OPERATIONS ----------------

    @Test
    public void testChainedOperations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(12.0, LengthUnit.INCHES);

        double result = q1.subtract(q2).divide(q3);

        assertEquals(8.0, result, DELTA);
    }
	
	
	
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
    


}
