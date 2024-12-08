package pu.fmi.wsp.hw.carmanagement.services;

import java.util.Optional;
import java.util.Set;

import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateCarDTO;

public interface CarService {
	Set<ResponseCarDTO> getAllCars(
			Optional<String> carMake, 
			Optional<Long> garageId,
			Optional<Integer> fromYear,
			Optional<Integer> toYear);
	ResponseCarDTO getCarById(Long id);
	ResponseCarDTO createCar(CreateCarDTO createCarDTO);
	ResponseCarDTO updateCar(Long id, UpdateCarDTO updateCarDTO);
	boolean deleteCarById(Long id);
}
