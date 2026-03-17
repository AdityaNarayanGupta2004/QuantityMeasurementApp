package com.app.quantitymeasurement.dto.request;

import com.app.quantitymeasurement.entity.QuantityDTO;

public class CompareRequestDTO {

    private QuantityDTO thisQuantity;
    private QuantityDTO thatQuantity;

    public CompareRequestDTO() {}

    public CompareRequestDTO(QuantityDTO thisQuantity, QuantityDTO thatQuantity) {
        this.thisQuantity = thisQuantity;
        this.thatQuantity = thatQuantity;
    }

    public QuantityDTO getThisQuantity() { return thisQuantity; }
    public void setThisQuantity(QuantityDTO thisQuantity) { this.thisQuantity = thisQuantity; }

    public QuantityDTO getThatQuantity() { return thatQuantity; }
    public void setThatQuantity(QuantityDTO thatQuantity) { this.thatQuantity = thatQuantity;
    }
}
