package pu.fmi.wsp.hw.carmanagement.model.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class UpdateCarDTO {
	@Size(min = 1, message = "Car make must be at least 1 character.")
	private String make;
	@Size(min = 1, message = "Car model must be at least 1 character.")
	private String model;
	@Min(value = 1886, message = "Invalid car production year.")
	private int productionYear;
	@Size(min = 1, message = "Car license plate must be at least 1 character.")
	private String licensePlate;
	private long[] garageIds;

	public UpdateCarDTO() {
	}

	public UpdateCarDTO(String make, String model, int productionYear, String licensePlate, long[] garageIds) {
		this.make = make;
		this.model = model;
		this.productionYear = productionYear;
		this.licensePlate = licensePlate;
		this.garageIds = garageIds;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public long[] getGarageIds() {
		return garageIds;
	}

	public void setGarageIds(long[] garageIds) {
		this.garageIds = garageIds;
	}
}
