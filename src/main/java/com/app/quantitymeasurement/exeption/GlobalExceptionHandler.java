package com.app.quantitymeasurement.exeption;


import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<Map<String, Object>> handleQuantityException(
            QuantityMeasurementException e) {
        logger.error("QuantityMeasurementException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error",     e.getMessage(),
                "type",      "QuantityMeasurementException",
                "timestamp", LocalDateTime.now().toString()
        ));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<Map<String, Object>> handleDatabaseException(
            DatabaseException e) {
        logger.error("DatabaseException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error",     e.getMessage(),
                "type",      "DatabaseException",
                "timestamp", LocalDateTime.now().toString()
        ));
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Map<String, Object>> handleUnsupportedOp(
            UnsupportedOperationException e) {
        logger.error("UnsupportedOperationException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error",     e.getMessage(),
                "type",      "UnsupportedOperationException",
                "timestamp", LocalDateTime.now().toString()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception e) {
        logger.error("Unhandled exception [{}]: {}", e.getClass().getSimpleName(),
                e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error",     "Internal server error",
                "type",      e.getClass().getSimpleName(),
                "timestamp", LocalDateTime.now().toString()
        ));
    }
}