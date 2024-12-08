package pu.fmi.wsp.hw.carmanagement.services.impl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import pu.fmi.wsp.hw.carmanagement.dao.GarageRepository;
import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.mapping.GarageMapper;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.GarageDailyAvailabilityReportDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateGarageDTO;
import pu.fmi.wsp.hw.carmanagement.services.GarageService;

@Service
public class GarageServiceImpl implements GarageService {
	private final GarageRepository garageRepository;
	private final GarageMapper garageMapper;
	
	public GarageServiceImpl(GarageRepository garageRepository, GarageMapper garageMapper) {
		this.garageRepository = garageRepository;
		this.garageMapper = garageMapper;
	}

	@Override
	public Set<ResponseGarageDTO> getAllGarages(Optional<String> city) {
		Set<Garage> fetchedGarages;
		if(city.isPresent()) {
			fetchedGarages = garageRepository.findByCityContainsIgnoreCase(city.get());
		}
		else {
			fetchedGarages = garageRepository.findAll();
		}
		
		return fetchedGarages
				.stream()
				.map(g -> garageMapper.toResponse(g))
				.collect(Collectors.toSet());
	}

	@Override
	public ResponseGarageDTO getGarageById(Long id) {
		Optional<Garage> fetchedGarage = garageRepository.findById(id);
		if(fetchedGarage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage was not found.");
		}
		
		return garageMapper.toResponse(fetchedGarage.get());
	}

	@Override
	public Set<GarageDailyAvailabilityReportDTO> getGarageReport(Long garageId, LocalDate startDate,
			LocalDate endDate) {
		Set<GarageDailyAvailabilityReportDTO> garageReports = garageRepository
				.getDailyAvailabilityReport(garageId, startDate, endDate);
		return garageReports;
	}

	@Override
	public ResponseGarageDTO createGarage(CreateGarageDTO createGarageDTO) {
		Garage newGarage = garageMapper.toEntity(createGarageDTO);
		newGarage = garageRepository.save(newGarage);
		return garageMapper.toResponse(newGarage);
	}

	@Override
	public ResponseGarageDTO updateGarage(Long id, UpdateGarageDTO updateGarageDTO) {
		Optional<Garage> fetchedGarage = garageRepository.findById(id);
		if(fetchedGarage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage was not found.");
		}
		
		Garage updatedGarage = garageMapper.toEntity(updateGarageDTO, fetchedGarage.get());
		updatedGarage = garageRepository.save(updatedGarage);
		return garageMapper.toResponse(updatedGarage);
	}

	@Override
	public boolean deleteGarageById(Long id) {
		Optional<Garage> fetchedGarage = garageRepository.findById(id);
		if(fetchedGarage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Garage was not found.");
		}
		
		garageRepository.delete(fetchedGarage.get());
		return true;
	}
}
