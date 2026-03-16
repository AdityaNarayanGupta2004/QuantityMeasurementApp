package quantitymeasurement.repository;

import quantitymeasurement.entity.QuantityMeasurementEntity;
import java.util.List;

public interface IQuantityMeasurementRepository {

    // Save a QuantityMeasurementEntity to the repository
    void save(QuantityMeasurementEntity entity);

    // Retrieve all QuantityMeasurementEntity instances from the repository
    List<QuantityMeasurementEntity> getAllMeasurements();

    // Main method for quick testing
    static void main(String[] args) {
        System.out.println("Testing IQuantityMeasurementRepository interface");
    }
}
