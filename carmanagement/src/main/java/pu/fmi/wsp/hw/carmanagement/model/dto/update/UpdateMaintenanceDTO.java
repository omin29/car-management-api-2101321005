package pu.fmi.wsp.hw.carmanagement.model.dto.update;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateMaintenanceDTO {
	@Min(value = 1, message = "Car ID must be a positive number.")
	private long carId;
	@Size(min = 1, message = "Service type must be at least 1 character.")
	private String serviceType;
	private LocalDate scheduledDate;
	@NotNull(message = "Garage ID must be specified.")
	@Min(value = 1, message = "Garage ID must be a positive number.")
	private long garageId;

	public UpdateMaintenanceDTO() {
	}

	public UpdateMaintenanceDTO(long carId, String serviceType, LocalDate scheduledDate, @NotNull long garageId) {
		this.carId = carId;
		this.serviceType = serviceType;
		this.scheduledDate = scheduledDate;
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

	public long getGarageId() {
		return garageId;
	}

	public void setGarageId(long garageId) {
		this.garageId = garageId;
	}
}
