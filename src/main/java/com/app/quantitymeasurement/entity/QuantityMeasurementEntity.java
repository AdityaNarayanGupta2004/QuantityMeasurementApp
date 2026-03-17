package com.app.quantitymeasurement.entity;

import jakarta.persistence.*;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key for H2

    // Inputs
    private double thisValue;
    private String thisUnit;
    private String thisMeasurementType;

    private double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    private String operation;

    // Result
    private double resultValue;
    private String resultUnit;
    @Setter
    private String resultMeasurementType;
    private String resultString;

    private boolean isError;
    private String errorMessage;

    // ── Constructors ────────────────────────────────────────────────────────

    public QuantityMeasurementEntity() {
        // Required for JPA
    }

    // For comparison results (e.g., "Equal" / "Not Equal")
    public QuantityMeasurementEntity(double thisValue, String thisUnit, String thisMeasurementType,
                                     double thatValue, String thatUnit, String thatMeasurementType,
                                     String operation, String resultString) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.resultString = resultString;
    }

    // For numeric result
    public QuantityMeasurementEntity(double thisValue, String thisUnit, String thisMeasurementType,
                                     double thatValue, String thatUnit, String thatMeasurementType,
                                     String operation, double resultValue, String resultUnit, String resultMeasurementType) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.resultValue = resultValue;
        this.resultUnit = resultUnit;
        this.resultMeasurementType = resultMeasurementType;
    }

    // For error
    public QuantityMeasurementEntity(double thisValue, String thisUnit, String thisMeasurementType,
                                     double thatValue, String thatUnit, String thatMeasurementType,
                                     String operation, String errorMessage, boolean isError) {
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;
        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;
        this.operation = operation;
        this.errorMessage = errorMessage;
        this.isError = isError;
    }

    // ── Getters & Setters ───────────────────────────────────────────────────

    public Long getId() { return id; }

    public double getThisValue() { return thisValue; }
    public void setThisValue(double thisValue) { this.thisValue = thisValue; }

    public String getThisUnit() { return thisUnit; }
    public void setThisUnit(String thisUnit) { this.thisUnit = thisUnit; }

    public String getThisMeasurementType() { return thisMeasurementType; }
    public void setThisMeasurementType(String thisMeasurementType) { this.thisMeasurementType = thisMeasurementType; }

    public double getThatValue() { return thatValue; }
    public void setThatValue(double thatValue) { this.thatValue = thatValue; }

    public String getThatUnit() { return thatUnit; }
    public void setThatUnit(String thatUnit) { this.thatUnit = thatUnit; }

    public String getThatMeasurementType() { return thatMeasurementType; }
    public void setThatMeasurementType(String thatMeasurementType) { this.thatMeasurementType = thatMeasurementType; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public double getResultValue() { return resultValue; }
    public void setResultValue(double resultValue) { this.resultValue = resultValue; }

    public String getResultUnit() { return resultUnit; }
    public void setResultUnit(String resultUnit) { this.resultUnit = resultUnit; }

    public String getResultMeasurementType() { return resultMeasurementType; }

    public String getResultString() { return resultString; }
    public void setResultString(String resultString) { this.resultString = resultString; }

    public boolean isError() { return isError; }
    public void setError(boolean error) { isError = error; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    // ── Overrides ──────────────────────────────────────────────────────────

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityMeasurementEntity other)) return false;
        return Double.compare(thisValue, other.thisValue) == 0
                && Double.compare(thatValue, other.thatValue) == 0
                && Objects.equals(thisUnit, other.thisUnit)
                && Objects.equals(thatUnit, other.thatUnit)
                && Objects.equals(thisMeasurementType, other.thisMeasurementType)
                && Objects.equals(thatMeasurementType, other.thatMeasurementType)
                && Objects.equals(operation, other.operation)
                && Objects.equals(resultString, other.resultString)
                && Double.compare(resultValue, other.resultValue) == 0
                && isError == other.isError
                && Objects.equals(errorMessage, other.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thisValue, thisUnit, thisMeasurementType,
                thatValue, thatUnit, thatMeasurementType,
                operation, resultValue, resultUnit, resultMeasurementType,
                resultString, isError, errorMessage);
    }

    @Override
    public String toString() {
        if (isError) {
            return String.format("[%s] %.1f %s + %.1f %s | ERROR: %s",
                    operation, thisValue, thisUnit, thatValue, thatUnit, errorMessage);
        }
        if (resultString != null) {
            return String.format("[%s] %.1f %s == %.1f %s | Result: %s",
                    operation, thisValue, thisUnit, thatValue, thatUnit, resultString);
        }
        return String.format("[%s] %.1f %s + %.1f %s | Result: %.2f %s",
                operation, thisValue, thisUnit, thatValue, thatUnit, resultValue, resultUnit);
    }
}