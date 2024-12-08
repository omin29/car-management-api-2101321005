package pu.fmi.wsp.hw.carmanagement.model.dto.update;

public class UpdateGarageDTO {
	private String name;
	private String location;
	private int capacity;
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
