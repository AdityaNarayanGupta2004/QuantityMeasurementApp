package com.app.quantitymeasurement.dto.request;

import com.app.quantitymeasurement.entity.QuantityDTO;

public class ConvertRequestDTO {

    private QuantityDTO thisQuantity;
    private String targetUnit;

    public ConvertRequestDTO() {}

    public ConvertRequestDTO(QuantityDTO thisQuantity, String targetUnit) {
        this.thisQuantity = thisQuantity;
        this.targetUnit = targetUnit;
    }

    public QuantityDTO getThisQuantity() {
        return thisQuantity;
    }

    public void setThisQuantity(QuantityDTO thisQuantity) {   // ✅ FIXED
        this.thisQuantity = thisQuantity;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }
}