package pu.fmi.wsp.hw.carmanagement.model.dto.response;

import jakarta.validation.constraints.NotNull;

public class ResponseGarageDTO {
	@NotNull
	private long id;
	@NotNull
	private String name;
	@NotNull
	private String location;
	@NotNull
	private String city;
	@NotNull
	private int capacity;
	
	public ResponseGarageDTO() {
	}

	public ResponseGarageDTO(long id, String name, String location, String city, int capacity) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.city = city;
		this.capacity = capacity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
