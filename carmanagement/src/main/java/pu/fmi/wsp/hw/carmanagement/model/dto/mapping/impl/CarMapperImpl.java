package pu.fmi.wsp.hw.carmanagement.model.dto.mapping.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import pu.fmi.wsp.hw.carmanagement.model.Car;
import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.mapping.CarMapper;
import pu.fmi.wsp.hw.carmanagement.model.dto.mapping.GarageMapper;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateCarDTO;

@Component
public class CarMapperImpl implements CarMapper {

	private final GarageMapper garageMapper;
	
	public CarMapperImpl(GarageMapper garageMapper) {
		this.garageMapper = garageMapper;
	}
	
	@Override
	public ResponseCarDTO toResponse(Car car) {
		return new ResponseCarDTO(
				car.getId(),
				car.getMake(),
				car.getModel(),
				car.getProductionYear(),
				car.getLicensePlate(),
				car.getGarages() == null ? Set.of() : car.getGarages()
						.stream()
						.map(g -> garageMapper.toResponse(g))
						.collect(Collectors.toSet()));
	}

	@Override
	public Car toEntity(CreateCarDTO createCarDTO, Set<Garage> garages) {
		Car newCar = new Car(
				null,
				createCarDTO.getMake(),
				createCarDTO.getModel(),
				createCarDTO.getProductionYear(),
				createCarDTO.getLicensePlate()
				);
		newCar.setGarages(garages);
		return newCar;
	}

	@Override
	public Car toEntity(UpdateCarDTO updateCarDTO, Car updatedCar, Set<Garage> garages) {
		updatedCar.setMake(updateCarDTO.getMake());
		updatedCar.setModel(updateCarDTO.getModel());
		updatedCar.setProductionYear(updateCarDTO.getProductionYear());
		updatedCar.setLicensePlate(updateCarDTO.getLicensePlate());
		updatedCar.setGarages(garages);
		return updatedCar;
	}
	
}
