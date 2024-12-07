package pu.fmi.wsp.hw.carmanagement.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String make;
	private String model;
	private Integer productionYear;
	private String licensePlate;

	@ManyToMany
	@JoinTable(
		name = "car_garage",
		joinColumns = @JoinColumn(name = "car_id"),
		inverseJoinColumns = @JoinColumn(name = "garage_id"))
	private Set<Garage> garages;
	@OneToMany(mappedBy = "car")
	private Set<Maintenance> maintenances;

	public Car() {
		this.garages = new HashSet<Garage>();
		this.maintenances = new HashSet<Maintenance>();
	}

	public Car(Long id, String make, String model, Integer productionYear, String licensePlate) {
		this();
		this.id = id;
		this.make = make;
		this.model = model;
		this.productionYear = productionYear;
		this.licensePlate = licensePlate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Integer getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(Integer productionYear) {
		this.productionYear = productionYear;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Set<Garage> getGarages() {
		return garages;
	}

	public void setGarages(Set<Garage> garages) {
		this.garages = garages;
	}

	public Set<Maintenance> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(Set<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}
}
