package pu.fmi.wsp.hw.carmanagement.model.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class UpdateGarageDTO {
	@Size(min = 1, message = "Garage name must be at least 1 character.")
	private String name;
	@Size(min = 1, message = "Garage location must be at least 1 character.")
	private String location;
	@Min(value = 1, message = "Garage capacity must be at least 1.")
	private int capacity;
	@Size(min = 1, message = "Garage city must be at least 1 character.")
	private String city;

	public UpdateGarageDTO() {
	}

	public UpdateGarageDTO(String name, String location, int capacity, String city) {
		this.name = name;
		this.location = location;
		this.capacity = capacity;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
