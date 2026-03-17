package com.app.quantitymeasurement.dto.request;

import com.app.quantitymeasurement.entity.QuantityDTO;
import lombok.Getter;
import lombok.Setter;

public class ArithmeticRequestDTO {

    @Setter
    private QuantityDTO thisQuantity;
    @Getter
    private QuantityDTO thatQuantity;
    private String targetUnit;

    public ArithmeticRequestDTO() {}

    public ArithmeticRequestDTO(QuantityDTO thisQuantity,
                                QuantityDTO thatQuantity,
                                String targetUnit) {
        this.thisQuantity = thisQuantity;
        this.thatQuantity = thatQuantity;
        this.targetUnit   = targetUnit;
    }

    public QuantityDTO getThisQuantity() { return thisQuantity; }

    public void setThatQuantity(QuantityDTO thatQuantity) { this.thatQuantity = thatQuantity; }

    public String getTargetUnit() { return targetUnit; }
    public void setTargetUnit(String targetUnit) { this.targetUnit = targetUnit; }
}
