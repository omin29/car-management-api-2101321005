package pu.fmi.wsp.hw.carmanagement.model.dto.mapping;

import java.util.Set;

import pu.fmi.wsp.hw.carmanagement.model.Car;
import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateCarDTO;

public interface CarMapper {
	ResponseCarDTO toResponse(Car car);
	Car toEntity(CreateCarDTO createCarDTO, Set<Garage> garages);
	Car toEntity(UpdateCarDTO updateCarDTO, Car updatedCar, Set<Garage> garages);
}
