package com.app.quantitymeasurement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

@Repository
public interface IQuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

    // Find all measurements by operation
    List<QuantityMeasurementEntity> findByOperation(String operation);

    // Find all measurements by measurement type
    List<QuantityMeasurementEntity> findByThisMeasurementType(String type);

    // Find all measurements with errors
    List<QuantityMeasurementEntity> findByIsError(boolean isError);

    // Count measurements by operation
    long countByOperation(String operation);
}