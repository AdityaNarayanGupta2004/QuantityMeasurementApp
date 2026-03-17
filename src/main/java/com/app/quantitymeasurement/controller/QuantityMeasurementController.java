package com.app.quantitymeasurement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.dto.request.ArithmeticRequestDTO;
import com.app.quantitymeasurement.dto.request.CompareRequestDTO;
import com.app.quantitymeasurement.dto.request.ConvertRequestDTO;
import com.app.quantitymeasurement.entity.QuantityDTO;
import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

@RestController
@RequestMapping("/api/measurements")
public class QuantityMeasurementController {

    private static final Logger logger =
            LoggerFactory.getLogger(QuantityMeasurementController.class);

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    // ── Compare ─────────────────────────────────────────────
    @PostMapping("/compare")
    public ResponseEntity<Boolean> compare(@RequestBody CompareRequestDTO request) {
        boolean result = service.compare(
                request.getThisQuantity(),
                request.getThatQuantity()
        );
        return ResponseEntity.ok(result);
    }

    // ── Convert ─────────────────────────────────────────────
    @PostMapping("/convert")
    public ResponseEntity<QuantityDTO> convert(@RequestBody ConvertRequestDTO request) {
        QuantityDTO result = service.convert(
                request.getThisQuantity(),
                request.getTargetUnit()
        );
        return ResponseEntity.ok(result);
    }

    // ── Add ─────────────────────────────────────────────────
    @PostMapping("/add")
    public ResponseEntity<QuantityDTO> add(@RequestBody ArithmeticRequestDTO request) {

        QuantityDTO result;

        if (request.getTargetUnit() != null) {
            result = service.add(
                    request.getThisQuantity(),
                    request.getThatQuantity(),
                    request.getTargetUnit()
            );
        } else {
            result = service.add(
                    request.getThisQuantity(),
                    request.getThatQuantity()
            );
        }

        return ResponseEntity.ok(result);
    }

    // ── Subtract ────────────────────────────────────────────
    @PostMapping("/subtract")
    public ResponseEntity<QuantityDTO> subtract(@RequestBody ArithmeticRequestDTO request) {

        QuantityDTO result;

        if (request.getTargetUnit() != null) {
            result = service.subtract(
                    request.getThisQuantity(),
                    request.getThatQuantity(),
                    request.getTargetUnit()
            );
        } else {
            result = service.subtract(
                    request.getThisQuantity(),
                    request.getThatQuantity()
            );
        }

        return ResponseEntity.ok(result);
    }

    // ── Divide ──────────────────────────────────────────────
    @PostMapping("/divide")
    public ResponseEntity<Double> divide(@RequestBody ArithmeticRequestDTO request) {
        double result = service.divide(
                request.getThisQuantity(),
                request.getThatQuantity()
        );
        return ResponseEntity.ok(result);
    }

    // ── History ─────────────────────────────────────────────
    @GetMapping("/history")
    public ResponseEntity<List<QuantityMeasurementEntity>> getHistory() {
        return ResponseEntity.ok(service.getAllMeasurements());
    }

    @GetMapping("/history/{operation}")
    public ResponseEntity<List<QuantityMeasurementEntity>> getByOperation(
            @PathVariable String operation) {

        return ResponseEntity.ok(
                service.getMeasurementsByOperation(operation.toUpperCase())
        );
    }
}