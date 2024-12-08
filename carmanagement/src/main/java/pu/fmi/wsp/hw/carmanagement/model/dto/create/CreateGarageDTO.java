package pu.fmi.wsp.hw.carmanagement.model.dto.create;

import jakarta.validation.constraints.NotNull;

public class CreateGarageDTO {
	@NotNull
	private String name;
	@NotNull
	private String location;
	@NotNull
	private String city;
	@NotNull
	private int capacity;

	public CreateGarageDTO() {
	}

	public CreateGarageDTO(@NotNull String name, @NotNull String location, @NotNull String city,
			@NotNull int capacity) {
		this.name = name;
		this.location = location;
		this.city = city;
		this.capacity = capacity;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
