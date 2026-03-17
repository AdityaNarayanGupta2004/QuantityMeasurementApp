package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {

    // Compare this quantity to that quantity to determine if they are equal in value.
    boolean compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    // Convert the given this quantity to a different unit with corresponding that unit.
    QuantityDTO convert(QuantityDTO thisQuantityDTO, String targetUnit);

    QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    QuantityDTO add(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, String targetUnit);

    QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    QuantityDTO subtract(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO, String targetUnit);

    double divide(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    List<QuantityMeasurementEntity> getAllMeasurements();
    List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation);
}