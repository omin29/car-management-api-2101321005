package pu.fmi.wsp.hw.carmanagement.model.dto.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateGarageDTO {
	@NotBlank
	@Size(min = 1, message = "Garage name must be at least 1 character.")
	private String name;
	@NotBlank
	@Size(min = 1, message = "Garage location must be at least 1 character.")
	private String location;
	@NotBlank
	@Size(min = 1, message = "Garage city must be at least 1 character.")
	private String city;
	@NotNull
	@Min(value = 1, message = "Garage capacity must be at least 1.")
	private int capacity;

	public CreateGarageDTO() {
	}

	public CreateGarageDTO(@NotBlank String name, @NotBlank String location, @NotBlank String city,
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
