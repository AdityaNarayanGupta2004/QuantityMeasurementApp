package quantitymeasurement.controller;

import quantitymeasurement.service.IQuantityMeasurementService;
import quantitymeasurement.entity.QuantityDTO;
import quantitymeasurement.repository.*;
import quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementController {
    private IQuantityMeasurementService quantityMeasurementService;

    public QuantityMeasurementController(
            IQuantityMeasurementService quantityMeasurementService) {
        this.quantityMeasurementService = quantityMeasurementService;
    }

    public boolean performComparison(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return quantityMeasurementService.compare(thisQuantityDTO, thatQuantityDTO);
    }

    public QuantityDTO performConversion(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return quantityMeasurementService.convert(thisQuantityDTO, thatQuantityDTO);
    }

    public QuantityDTO performAddition(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return quantityMeasurementService.add(thisQuantityDTO, thatQuantityDTO);
    }

    public QuantityDTO performAddition(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO,
                    QuantityDTO targetUnitDTO) {
        return quantityMeasurementService.add(thisQuantityDTO, thatQuantityDTO,
                targetUnitDTO);
    }

    public QuantityDTO performSubtraction(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return quantityMeasurementService.subtract(thisQuantityDTO, thatQuantityDTO);
    }

    public QuantityDTO performSubtraction(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO,
                       QuantityDTO targetUnitDTO) {
        return quantityMeasurementService.subtract(thisQuantityDTO, thatQuantityDTO,
                targetUnitDTO);
    }

    public double performDivision(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return quantityMeasurementService.divide(thisQuantityDTO, thatQuantityDTO);
    }

    // Main method for testing purposes
    public static void main(String[] args) {
        IQuantityMeasurementRepository repo = QuantityMeasurementCacheRepository.getInstance();
        QuantityMeasurementController controller = new QuantityMeasurementController(new QuantityMeasurementServiceImpl(repo));

        boolean result = controller.performComparison(
                new QuantityDTO(1.0,  QuantityDTO.LengthUnit.FEET),
                new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES));
        System.out.println("1 FEET == 12 INCHES: " + result);
    }
}
