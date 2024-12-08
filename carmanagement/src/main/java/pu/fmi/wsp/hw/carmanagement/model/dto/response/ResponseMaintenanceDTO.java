package pu.fmi.wsp.hw.carmanagement.model.dto.response;

import java.time.LocalDate;

public class ResponseMaintenanceDTO {
	private long id;
	private long carId;
	private String carName;
	private String serviceType;
	private LocalDate scheduledDate;
	private long garageId;
	private String garageName;

	public ResponseMaintenanceDTO() {
	}

	public ResponseMaintenanceDTO(long id, long carId, String carName, String serviceType, LocalDate scheduledDate,
			long garageId, String garageName) {
		this.id = id;
		this.carId = carId;
		this.carName = carName;
		this.serviceType = serviceType;
		this.scheduledDate = scheduledDate;
		this.garageId = garageId;
		this.garageName = garageName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public LocalDate getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(LocalDate scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public long getGarageId() {
		return garageId;
	}

	public void setGarageId(long garageId) {
		this.garageId = garageId;
	}

	public String getGarageName() {
		return garageName;
	}

	public void setGarageName(String garageName) {
		this.garageName = garageName;
	}
}
