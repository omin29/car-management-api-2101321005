package pu.fmi.wsp.hw.carmanagement.services.impl;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import pu.fmi.wsp.hw.carmanagement.dao.CarRepository;
import pu.fmi.wsp.hw.carmanagement.dao.GarageRepository;
import pu.fmi.wsp.hw.carmanagement.model.Car;
import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.mapping.CarMapper;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateCarDTO;
import pu.fmi.wsp.hw.carmanagement.services.CarService;

@Service
public class CarServiceImpl implements CarService {
	private final CarRepository carRepository;
	private final GarageRepository garageRepository;
	private final CarMapper carMapper;
	
	public CarServiceImpl(CarRepository carRepository, GarageRepository garageRepository, CarMapper carMapper) {
		this.carRepository = carRepository;
		this.garageRepository = garageRepository;
		this.carMapper = carMapper;
	}

	@Override
	public Set<ResponseCarDTO> getAllCars(Optional<String> carMake, Optional<Long> garageId, 
			Optional<Integer> fromYear,	Optional<Integer> toYear) {
		if(carMake.isPresent() && carMake.get().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "carMake must be at least 1 character.");
		}
		
		if(garageId.isPresent() && garageId.get() <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "garageId must be a positive number.");
		}
		
		if(fromYear.isPresent() && fromYear.get() <= 0 ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "fromYear must be a positive number.");
		}
		
		if(toYear.isPresent() && toYear.get() <= 0 ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "toYear must be a positive number.");
		}
		
		if((fromYear.isPresent() && toYear.isEmpty()) || 
				(fromYear.isEmpty() && toYear.isPresent()) || 
				(fromYear.isPresent() && toYear.isPresent() && fromYear.get() > toYear.get())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid year range.");
		}
		
		Set<Car> fetchedCars = carRepository.findAll(
				carMake.orElse(null), 
				garageId.orElse(null),
				fromYear.orElse(null),
				toYear.orElse(null));
		
		return fetchedCars
				.stream()
				.map(c -> carMapper.toResponse(c))
				.collect(Collectors.toSet());
	}

	@Override
	public ResponseCarDTO getCarById(Long id) {
		if(id <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id must be a positive number.");
		}
		
		Optional<Car> fetchedCar = carRepository.findById(id);
		if(fetchedCar.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car was not found.");
		}
		
		return carMapper.toResponse(fetchedCar.get());
	}

	@Override
	public ResponseCarDTO createCar(CreateCarDTO createCarDTO) {
		Set<Garage> garages = garageRepository.findAllById(
				Arrays
					.stream(createCarDTO.getGarageIds())
					.boxed()
					.collect(Collectors.toSet()));
		Car newCar = carMapper.toEntity(createCarDTO, garages);
		newCar = carRepository.save(newCar);
		return carMapper.toResponse(newCar);
	}

	@Override
	public ResponseCarDTO updateCar(Long id, UpdateCarDTO updateCarDTO) {
		if(id <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id must be a positive number.");
		}
		
		Optional<Car> fetchedCar = carRepository.findById(id);
		if(fetchedCar.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car was not found.");
		}
		
		Set<Garage> garages = null;
		if(updateCarDTO.getGarageIds() != null) {
			garages = garageRepository.findAllById(
					Arrays
						.stream(updateCarDTO.getGarageIds())
						.boxed()
						.collect(Collectors.toSet()));
		}
		
		Car updatedCar = carMapper.toEntity(updateCarDTO, fetchedCar.get(), garages);
		updatedCar = carRepository.save(updatedCar);
		return carMapper.toResponse(updatedCar);
		
	}

	@Override
	public boolean deleteCarById(Long id) {
		if(id <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id must be a positive number.");
		}
		
		Optional<Car> fetchedCar = carRepository.findById(id);
		if(fetchedCar.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car was not found.");
		}
		
		carRepository.delete(fetchedCar.get());
		return true;
	}
}
