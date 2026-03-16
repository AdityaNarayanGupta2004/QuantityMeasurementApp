package quantitymeasurement;

import quantitymeasurement.controller.QuantityMeasurementController;
import quantitymeasurement.entity.QuantityDTO;
import quantitymeasurement.repository.IQuantityMeasurementRepository;
import quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

	// Singleton instance
	private static QuantityMeasurementApp instance;

	// Controller reference
	public QuantityMeasurementController controller;

	// Repository reference
	public IQuantityMeasurementRepository repository;

	// Private constructor
	private QuantityMeasurementApp() {

		repository = QuantityMeasurementCacheRepository.getInstance();

		QuantityMeasurementServiceImpl service =
				new QuantityMeasurementServiceImpl(repository);

		controller = new QuantityMeasurementController(service);
	}

	// Get singleton instance
	public static QuantityMeasurementApp getInstance() {
		if (instance == null) {
			instance = new QuantityMeasurementApp();
		}
		return instance;
	}

	// Main method
	public static void main(String[] args) {

		QuantityMeasurementApp app = QuantityMeasurementApp.getInstance();
		QuantityMeasurementController controller = app.controller;

		System.out.println("---------------------------------------------");
		System.out.println("Quantity Measurement App (N-Tier Architecture)");
		System.out.println("---------------------------------------------");

		// Length Equality
		System.out.println("\n--- Length Equality ---");

		QuantityDTO feet = new QuantityDTO(1.0, QuantityDTO.LengthUnit.FEET);
		QuantityDTO inches12 = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);
		System.out.println("1 FEET == 12 INCHES : " + controller.performComparison(feet, inches12));

		QuantityDTO yard = new QuantityDTO(1.0, QuantityDTO.LengthUnit.YARDS);
		QuantityDTO inches36 = new QuantityDTO(36.0, QuantityDTO.LengthUnit.INCHES);
		System.out.println("1 YARD == 36 INCHES : " + controller.performComparison(yard, inches36));


		// Weight Conversion & Comparison
		System.out.println("\n-*-*- Weight Conversion & Comparison -*-*-");

		QuantityDTO grams = new QuantityDTO(1000.0, QuantityDTO.WeightUnit.GRAM);
		QuantityDTO kg = new QuantityDTO(1.0, QuantityDTO.WeightUnit.KILOGRAM);

		System.out.println("1000 GRAM == 1 KG : " + controller.performComparison(grams, kg));
		System.out.println("Convert 1000 GRAM -> KG : " + controller.performConversion(grams, kg));


		// Weight Addition
		System.out.println("\n--- Weight Addition ---");

		QuantityDTO pounds = new QuantityDTO(2.20462, QuantityDTO.WeightUnit.POUND);

		System.out.println("1 KG + 2.20462 LB -> KG : " +
				controller.performAddition(kg, pounds));

		System.out.println("1 KG + 2.20462 LB -> GRAM : " +
				controller.performAddition(
						kg,
						pounds,
						new QuantityDTO(0, QuantityDTO.WeightUnit.GRAM)
				));


		// Volume Operations
		System.out.println("\n--- Volume Operations ---");

		QuantityDTO litre = new QuantityDTO(1.0, QuantityDTO.VolumeUnit.LITRE);
		QuantityDTO ml = new QuantityDTO(1000.0, QuantityDTO.VolumeUnit.MILLILITRE);
		QuantityDTO gallon = new QuantityDTO(1.0, QuantityDTO.VolumeUnit.GALLON);

		System.out.println("1 L == 1000 mL : " + controller.performComparison(litre, ml));
		System.out.println("Convert 1 GAL -> L : " + controller.performConversion(gallon, litre));
		System.out.println("1 L + 1000 mL : " + controller.performAddition(litre, ml));


		// Temperature Conversion
		System.out.println("\n--- Temperature Conversion & Comparison ---");

		QuantityDTO celsius = new QuantityDTO(100.0, QuantityDTO.TemperatureUnit.CELSIUS);
		QuantityDTO fahr = new QuantityDTO(212.0, QuantityDTO.TemperatureUnit.FAHRENHEIT);

		System.out.println("100 C == 212 F : " + controller.performComparison(celsius, fahr));

		System.out.println("Convert 100 C -> F : " +
				controller.performConversion(
						celsius,
						new QuantityDTO(0, QuantityDTO.TemperatureUnit.FAHRENHEIT)
				));


		// Subtraction & Division
		System.out.println("\n--- Subtraction & Division ---");

		QuantityDTO q1 = new QuantityDTO(2.0, QuantityDTO.LengthUnit.FEET);
		QuantityDTO q2 = new QuantityDTO(12.0, QuantityDTO.LengthUnit.INCHES);

		System.out.println("2 FEET - 12 INCHES : " +
				controller.performSubtraction(q1, q2));

		System.out.println("2 FEET / 12 INCHES : " +
				controller.performDivision(q1, q2));


		System.out.println("---------------------------------------------");
		System.out.println("Repository: "
				+ app.repository.getAllMeasurements().size()
				+ " operation(s) recorded.");
		System.out.println("---------------------------------------------");
	}
}
