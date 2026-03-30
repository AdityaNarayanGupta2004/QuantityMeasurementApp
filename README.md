# Quantity Measurement Application

---

## Description  
A Java-based application that demonstrates progressively advanced object-oriented design patterns through a series of use cases covering measurement equality, unit conversion, and arithmetic operations across multiple measurement categories (length, weight, volume).

---

## Project Structure

```
├── QunatityMeasurementApp/            (Repository Name and Project Name)
│   │
│   ├── main      (default branch)
│   ├── dev       (Branch Name)
│   ├── feature/UC1-FeetEquality
│   ├── feature/UC2-InchEquality
│   ├── feature/UC3-GenericLength
│   ├── feature/UC4-YardEquality
│   ├── feature/UC5-UnitConversion
│   ├── feature/UC6-UnitAddition
│   ├── feature/UC7-TargetUnitAddition
│   ├── feature/UC8-StandaloneAddition
│   ├── feature/UC9-WeightMeasurement
│   ├── feature/UC10-GenericEquality
│   ├── feature/UC11-VolumeMeasurement
│   ├── feature/UC12-SubtractionDivisionOperation
```

---
## Use Cases

### UC1 – Feet Measurement Equality
Introduces the `Feet` inner class with a proper `equals()` override using `Double.compare()` for floating-point safety.

**Concepts:** Object equality, floating-point comparison, null checking, type safety.

---

### UC2 – Feet and Inches Measurement Equality
Extends UC1 by adding a separate `Inches` class. Highlights the DRY violation of maintaining two nearly identical classes.

**Concepts:** Same as UC1, applied to a second unit type.

---

### UC3 – Generic Quantity Class (DRY Principle)
Refactors `Feet` and `Inches` into a single `QuantityLength` class backed by a `LengthUnit` enum with conversion factors.

**Concepts:** DRY principle, enum usage, unit abstraction, cross-unit equality (1 foot = 12 inches).

---

### UC4 – Extended Unit Support (Yards & Centimeters)
Adds `YARDS` and `CENTIMETERS` to the `LengthUnit` enum. No changes to `QuantityLength` are needed, validating the scalable design.

**Conversions:**
- 1 yard = 3 feet = 36 inches
- 1 cm = 0.393701 inches

---

### UC5 – Unit-to-Unit Conversion
Exposes a `convert(value, sourceUnit, targetUnit)` method. Normalizes values through the base unit (feet) before converting to the target unit.

**Concepts:** Base unit normalization, bidirectional conversion, method design, API usability.

---

### UC6 – Addition of Two Length Units
Adds two `QuantityLength` objects of potentially different units. The result is expressed in the unit of the first operand.

**Concepts:** Arithmetic on value objects, immutability, unit conversion reuse, commutativity.

---

### UC7 – Addition with Explicit Target Unit
Extends UC6 by allowing the caller to specify any supported unit for the result.

**Concepts:** Method overloading, explicit parameter passing, functional approach.

---

### UC8 – Standalone LengthUnit Enum with Conversion Responsibility
Extracts `LengthUnit` from inside `QuantityLength` into a standalone top-level class. Assigns conversion responsibility (`convertToBaseUnit`, `convertFromBaseUnit`) to the enum itself.

**Concepts:** SRP, separation of concerns, dependency inversion, circular dependency elimination.

---

### UC9 – Weight Measurement (Kilogram, Gram, Pound)
Introduces `WeightUnit` and `QuantityWeight`, mirroring the length design. Demonstrates support for a second, independent measurement category.

**Conversions:**
- 1 kg = 1000 g
- 1 lb ≈ 0.453592 kg

**Concepts:** Multi-category support, category type safety, equals/hashCode contract.

---

### UC10 – Generic Quantity Class with IMeasurable Interface
Eliminates the duplication between `QuantityLength` and `QuantityWeight` by introducing:
- `IMeasurable` interface for all unit enums
- Generic `Quantity<U extends IMeasurable>` class replacing all category-specific Quantity classes
- Simplified `QuantityMeasurementApp` with generic demonstration methods

**Concepts:** Generic programming, bounded type parameters, interface-based design, OCP, LSP, composition over inheritance.

---

### UC11 – Volume Measurement (Litre, Millilitre, Gallon)
Adds a `VolumeUnit` enum implementing `IMeasurable`. No changes to `Quantity<U>` or `QuantityMeasurementApp` are required, validating true architectural scalability.

**Conversions:**
- 1 L = 1000 mL
- 1 gallon ≈ 3.78541 L

**Concepts:** Scalability validation, DRY at scale, pattern replication across categories.
---

### UC12 – Subtraction and Division Operations on Quantity Measurements
Adds a Subtraction and Division method in the Quantity class to validate the subtraction and Division

**Conversions:**
- 1 L = 1000 mL
- 1000ml = 1L
- 1 gallon ≈ 3.78541 L

---
