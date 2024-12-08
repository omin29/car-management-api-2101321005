package pu.fmi.wsp.hw.carmanagement.model.dto.response;

import java.util.Set;

public class ResponseCarDTO {
	private long id;
	private String make;
	private String model;
	private int productionYear;
	private String licensePlate;
	private Set<ResponseGarageDTO> garages;

	public ResponseCarDTO() {
	}

	public ResponseCarDTO(long id, String make, String model, int productionYear, String licensePlate,
			Set<ResponseGarageDTO> garages) {
		this.id = id;
		this.make = make;
		this.model = model;
		this.productionYear = productionYear;
		this.licensePlate = licensePlate;
		this.garages = garages;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Set<ResponseGarageDTO> getGarages() {
		return garages;
	}

	public void setGarages(Set<ResponseGarageDTO> garages) {
		this.garages = garages;
	}
}
