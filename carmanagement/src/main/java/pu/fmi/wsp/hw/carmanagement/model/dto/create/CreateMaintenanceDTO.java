package pu.fmi.wsp.hw.carmanagement.model.dto.create;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class CreateMaintenanceDTO {
	@NotNull
	private long garageId;
	@NotNull
	private long carId;
	@NotNull
	private String serviceType;
	@NotNull
	private LocalDate scheduledDate;
	
	public CreateMaintenanceDTO() {
	}

	public CreateMaintenanceDTO(@NotNull long garageId, @NotNull long carId, @NotNull String serviceType,
			@NotNull LocalDate scheduledDate) {
		this.garageId = garageId;
		this.carId = carId;
		this.serviceType = serviceType;
		this.scheduledDate = scheduledDate;
	}

	public long getGarageId() {
		return garageId;
	}

	public void setGarageId(long garageId) {
		this.garageId = garageId;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
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
}
