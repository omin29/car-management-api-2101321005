package pu.fmi.wsp.hw.carmanagement.model.dto.create;

public class CreateCarDTO {
	private String make;
	private String model;
	private int productionYear;
	private String licensePlate;
	private long[] garageIds;
	
	public CreateCarDTO() {
	}

	public CreateCarDTO(String make, String model, int productionYear, String licensePlate, long[] garageIds) {
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
