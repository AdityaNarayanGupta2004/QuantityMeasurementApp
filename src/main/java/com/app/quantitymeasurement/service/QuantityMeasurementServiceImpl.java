package com.app.quantitymeasurement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.core.Quantity;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.entity.QuantityModel;
import com.app.quantitymeasurement.exeption.QuantityMeasurementException;
import com.app.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.app.quantitymeasurement.model.IMeasurable;
import com.app.quantitymeasurement.model.LengthUnit;
import com.app.quantitymeasurement.model.TemperatureUnit;
import com.app.quantitymeasurement.model.VolumeUnit;
import com.app.quantitymeasurement.model.WeightUnit;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private static final Logger logger =
            LoggerFactory.getLogger(QuantityMeasurementServiceImpl.class);

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
        logger.info("QuantityMeasurementServiceImpl initialized");
    }

    // ── Unit resolution ───────────────────────────────────────────────────────
    private IMeasurable resolveUnit(String measurementType, String unitName) {
        return switch (measurementType) {
            case "LengthUnit"      -> LengthUnit.FEET.getUnitInstance(unitName);
            case "WeightUnit"      -> WeightUnit.KILOGRAM.getUnitInstance(unitName);
            case "VolumeUnit"      -> VolumeUnit.LITRE.getUnitInstance(unitName);
            case "TemperatureUnit" -> TemperatureUnit.CELSIUS.getUnitInstance(unitName);
            default -> throw new QuantityMeasurementException("Unknown measurement type: " + measurementType);
        };
    }

    private QuantityModel<IMeasurable> toModel(QuantityDTO dto) {
        if (dto == null) throw new QuantityMeasurementException("QuantityDTO cannot be null");
        IMeasurable unit = resolveUnit(dto.getMeasurementType(), dto.getUnit());
        return new QuantityModel<>(dto.getValue(), unit);
    }

    private QuantityDTO toDTO(QuantityModel<IMeasurable> model) {
        return new QuantityDTO(model.getValue(), model.getUnit().getUnitName(),
                model.getUnit().getMeasurementType());
    }

    // ── Entity builder helper ────────────────────────────────────────────────
    private QuantityMeasurementEntity buildEntity(
            QuantityModel<IMeasurable> m1,
            QuantityModel<IMeasurable> m2,
            String operation,
            String resultString,
            QuantityModel<IMeasurable> resultModel,
            boolean isError,
            String errorMessage) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        if (m1 != null) {
            entity.setThisValue(m1.getValue());
            entity.setThisUnit(m1.getUnit().getUnitName());
            entity.setThisMeasurementType(m1.getUnit().getMeasurementType());
        }
        if (m2 != null) {
            entity.setThatValue(m2.getValue());
            entity.setThatUnit(m2.getUnit().getUnitName());
            entity.setThatMeasurementType(m2.getUnit().getMeasurementType());
        }
        if (resultModel != null) {
            entity.setResultValue(resultModel.getValue());
            entity.setResultUnit(resultModel.getUnit().getUnitName());
            entity.setResultMeasurementType(resultModel.getUnit().getMeasurementType());
        }
        entity.setOperation(operation);
        entity.setResultString(resultString);
        entity.setError(isError);
        entity.setErrorMessage(errorMessage);
        return entity;
    }

    // ── Operations ──────────────────────────────────────────────────────────

    @Override
    public boolean compare(QuantityDTO dto1, QuantityDTO dto2) {
        QuantityModel<IMeasurable> m1 = toModel(dto1);
        QuantityModel<IMeasurable> m2 = toModel(dto2);

        try {
            if (!m1.getUnit().getClass().equals(m2.getUnit().getClass())) {
                repository.save(buildEntity(m1, m2, "COMPARE", "Not Equal", null, false, null));
                return false;
            }

            boolean equal = Math.abs(m1.getUnit().convertToBaseUnit(m1.getValue()) -
                    m2.getUnit().convertToBaseUnit(m2.getValue())) <= 1e-5;

            repository.save(buildEntity(m1, m2, "COMPARE", equal ? "Equal" : "Not Equal", null, false, null));
            return equal;

        } catch (Exception e) {
            repository.save(buildEntity(m1, m2, "COMPARE", null, null, true, e.getMessage()));
            throw new QuantityMeasurementException("Comparison failed: " + e.getMessage(), e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public QuantityDTO convert(QuantityDTO dto, String targetUnitName) {
        QuantityModel<IMeasurable> m = toModel(dto);
        try {
            IMeasurable targetUnit = resolveUnit(dto.getMeasurementType(), targetUnitName);
            Quantity<IMeasurable> converted = new Quantity<>(m.getValue(), m.getUnit()).convertTo(targetUnit);
            QuantityModel<IMeasurable> resultModel = new QuantityModel<>(converted.getValue(), converted.getUnit());

            repository.save(buildEntity(m, null, "CONVERT", null, resultModel, false, null));
            return toDTO(resultModel);

        } catch (Exception e) {
            repository.save(buildEntity(m, null, "CONVERT", null, null, true, e.getMessage()));
            throw new QuantityMeasurementException("Conversion failed: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO add(QuantityDTO dto1, QuantityDTO dto2) {
        return add(dto1, dto2, dto1.getUnit());
    }

    @Override
    public QuantityDTO add(QuantityDTO dto1, QuantityDTO dto2, String targetUnitName) {
        QuantityModel<IMeasurable> m1 = toModel(dto1);
        QuantityModel<IMeasurable> m2 = toModel(dto2);
        QuantityModel<IMeasurable> target = new QuantityModel<>(0.0, resolveUnit(dto1.getMeasurementType(), targetUnitName));

        try {
            Quantity<IMeasurable> result = new Quantity<>(m1.getValue(), m1.getUnit())
                    .add(new Quantity<>(m2.getValue(), m2.getUnit()), target.getUnit());
            QuantityModel<IMeasurable> resultModel = new QuantityModel<>(result.getValue(), result.getUnit());

            repository.save(buildEntity(m1, m2, "ADD", null, resultModel, false, null));
            return toDTO(resultModel);

        } catch (Exception e) {
            repository.save(buildEntity(m1, m2, "ADD", null, null, true, e.getMessage()));
            throw new QuantityMeasurementException("Addition failed: " + e.getMessage(), e);
        }
    }

    @Override
    public QuantityDTO subtract(QuantityDTO dto1, QuantityDTO dto2) {
        return subtract(dto1, dto2, dto1.getUnit());
    }

    @Override
    public QuantityDTO subtract(QuantityDTO dto1, QuantityDTO dto2, String targetUnitName) {
        QuantityModel<IMeasurable> m1 = toModel(dto1);
        QuantityModel<IMeasurable> m2 = toModel(dto2);
        QuantityModel<IMeasurable> target = new QuantityModel<>(0.0, resolveUnit(dto1.getMeasurementType(), targetUnitName));

        try {
            Quantity<IMeasurable> result = new Quantity<>(m1.getValue(), m1.getUnit())
                    .subtract(new Quantity<>(m2.getValue(), m2.getUnit()), target.getUnit());
            QuantityModel<IMeasurable> resultModel = new QuantityModel<>(result.getValue(), result.getUnit());

            repository.save(buildEntity(m1, m2, "SUBTRACT", null, resultModel, false, null));
            return toDTO(resultModel);

        } catch (Exception e) {
            repository.save(buildEntity(m1, m2, "SUBTRACT", null, null, true, e.getMessage()));
            throw new QuantityMeasurementException("Subtraction failed: " + e.getMessage(), e);
        }
    }

    @Override
    public double divide(QuantityDTO dto1, QuantityDTO dto2) {
        QuantityModel<IMeasurable> m1 = toModel(dto1);
        QuantityModel<IMeasurable> m2 = toModel(dto2);

        try {
            double result = new Quantity<>(m1.getValue(), m1.getUnit())
                    .divide(new Quantity<>(m2.getValue(), m2.getUnit()));

            repository.save(buildEntity(m1, m2, "DIVIDE", String.valueOf(result), null, false, null));
            return result;

        } catch (Exception e) {
            repository.save(buildEntity(m1, m2, "DIVIDE", null, null, true, e.getMessage()));
            throw new QuantityMeasurementException("Division failed: " + e.getMessage(), e);
        }
    }

    // ── Query history ────────────────────────────────────────────────────────
    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return repository.findAll();
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        return repository.findByOperation(operation);
    }
}