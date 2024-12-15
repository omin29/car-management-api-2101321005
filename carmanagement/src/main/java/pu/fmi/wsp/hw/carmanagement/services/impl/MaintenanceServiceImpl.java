package pu.fmi.wsp.hw.carmanagement.services.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import pu.fmi.wsp.hw.carmanagement.dao.CarRepository;
import pu.fmi.wsp.hw.carmanagement.dao.GarageRepository;
import pu.fmi.wsp.hw.carmanagement.dao.MaintenanceRepository;
import pu.fmi.wsp.hw.carmanagement.model.Car;
import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.Maintenance;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.mapping.MaintenanceMapper;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.GarageDailyAvailabilityReportDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.MonthlyRequestsReportDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.services.MaintenanceService;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
	private final MaintenanceRepository maintenanceRepository;
	private final CarRepository carRepository;
	private final GarageRepository garageRepository;
	private final MaintenanceMapper maintenanceMapper;
	
	public MaintenanceServiceImpl(
			MaintenanceRepository maintenanceRepository, CarRepository carRepository, 
			GarageRepository garageRepository, MaintenanceMapper maintenanceMapper) {
		this.maintenanceRepository = maintenanceRepository;
		this.carRepository = carRepository;
		this.garageRepository = garageRepository;
		this.maintenanceMapper = maintenanceMapper;
	}

	@Override
	public Set<ResponseMaintenanceDTO> getAllMaintenances(Optional<Long> carId, Optional<Long> garageId,
			Optional<LocalDate> startDate, Optional<LocalDate> endDate) {
		if(carId.isPresent() && carId.get() <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "carId must be a positive number.");
		}
		
		if(garageId.isPresent() && garageId.get() <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "garageId must be a positive number.");
		}
		
		if(startDate.isPresent() && endDate.isPresent() && startDate.get().isAfter(endDate.get())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date range.");
		}
		
		Set<Maintenance> fetchedMaintenances = maintenanceRepository
				.findAll(
						carId.orElse(null),
						garageId.orElse(null),
						startDate.orElse(null),
						endDate.orElse(null));
		
		return fetchedMaintenances
				.stream()
				.map(m -> maintenanceMapper.toResponse(m))
				.collect(Collectors.toSet());
	}

	@Override
	public ResponseMaintenanceDTO getMaintenanceById(Long id) {
		if(id <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id must be a positive number.");
		}
		
		Optional<Maintenance> fetchedMaintenance = maintenanceRepository.findById(id);
		if(fetchedMaintenance.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance was not found.");
		}
		
		return maintenanceMapper.toResponse(fetchedMaintenance.get());
	}

	@Override
	public Set<MonthlyRequestsReportDTO> getMonthlyRequestsReport(Long garageId, String startMonth, String endMonth) {
		if(garageId <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "garageId must be a positive number.");
		}
		
		YearMonth startYearMonth = null;
		
		try {
			startYearMonth = YearMonth.parse(startMonth, DateTimeFormatter.ofPattern("uuuu-MM"));
		} catch (DateTimeParseException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid startMonth. It must be provided in yyyy-mm format.");
		}
		
		YearMonth endYearMonth = null;
		
		try {
			endYearMonth = YearMonth.parse(endMonth, DateTimeFormatter.ofPattern("uuuu-MM"));
		} catch (DateTimeParseException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid endMonth. It must be provided in yyyy-mm format.");
		}
		
		if(startYearMonth.isAfter(endYearMonth)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid month range.");
		}
		
		LocalDate startDate = startYearMonth.atDay(1);
		LocalDate endDate = endYearMonth.atEndOfMonth();
		Set<MonthlyRequestsReportDTO> monthlyRequestsReports = maintenanceRepository
				.getMonthlyRequestsReport(garageId, startDate, endDate);
		
		//Populating months without requests
		for(YearMonth current = startYearMonth, end = endYearMonth; !current.isAfter(end); current = current.plusMonths(1)) {
			final YearMonth currentYearMonth = current; //Avoiding compiler error
			if(!monthlyRequestsReports
					.stream()
					.anyMatch(r -> r.getYearMonth().equals(currentYearMonth))) {
				monthlyRequestsReports.add(new MonthlyRequestsReportDTO(current, 0));
			}
		}
		
		//For returning chronologically sorted reports
		monthlyRequestsReports = new TreeSet<>(monthlyRequestsReports);
		
		return monthlyRequestsReports;
	}

	@Override
	public ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO createMaintenanceDTO) {
		Optional<GarageDailyAvailabilityReportDTO> garageReport = garageRepository
				.getDailyAvailabilityReport(
						createMaintenanceDTO.getGarageId(), 
						createMaintenanceDTO.getScheduledDate(), 
						createMaintenanceDTO.getScheduledDate())
				.stream()
				.findFirst();
		
		//If there isn't a report for the scheduled date, then the garage is at maximum available capacity.
		if(garageReport.isPresent() && garageReport.get().getAvailableCapacity() <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create maintenance request because there isn't available space in the specified garage.");
		}
		
		Optional<Car> car = carRepository.findById(createMaintenanceDTO.getCarId());
		if(car.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create maintenance request because specified car wasn't found.");
		}
		
		Optional<Garage> garage = garageRepository.findById(createMaintenanceDTO.getGarageId());
		if(garage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to create maintenance request because specified garage wasn't found.");
		}
	
		Maintenance newMaintenance = maintenanceMapper.toEntity(createMaintenanceDTO, car.get(), garage.get());
		newMaintenance = maintenanceRepository.save(newMaintenance);
		return maintenanceMapper.toResponse(newMaintenance);
	}

	@Override
	public ResponseMaintenanceDTO updateMaintenance(Long id, UpdateMaintenanceDTO updateMaintenanceDTO) {
		if(id <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id must be a positive number.");
		}
		
		Optional<Maintenance> fetchedMaintenance = maintenanceRepository.findById(id);
		if(fetchedMaintenance.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance was not found.");
		}
		
		Optional<GarageDailyAvailabilityReportDTO> garageReport = garageRepository
				.getDailyAvailabilityReport(
						updateMaintenanceDTO.getGarageId(), 
						updateMaintenanceDTO.getScheduledDate(), 
						updateMaintenanceDTO.getScheduledDate())
				.stream()
				.findFirst();
		
		boolean sameGarageAndDate = 
				fetchedMaintenance.get().getGarage().getId() == updateMaintenanceDTO.getGarageId() &&
				fetchedMaintenance.get().getScheduledDate().equals(updateMaintenanceDTO.getScheduledDate());
		/*If there isn't a report for the scheduled date, then the garage is at maximum available capacity. 
		  There will be no problem with garage availability if the garage and scheduled date aren't changed.*/
		if(garageReport.isPresent() && 
				garageReport.get().getAvailableCapacity() <= 0 && 
				!sameGarageAndDate) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update maintenance request because there isn't available space in the specified garage.");
		}
		
		Optional<Car> car = carRepository.findById(updateMaintenanceDTO.getCarId());
		if(car.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update maintenance request because specified car wasn't found.");
		}
		
		Optional<Garage> garage = garageRepository.findById(updateMaintenanceDTO.getGarageId());
		if(garage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update maintenance request because specified garage wasn't found.");
		}
		
		Maintenance updatedMaintenance = maintenanceMapper.toEntity(updateMaintenanceDTO, fetchedMaintenance.get(), car.get(), garage.get());
		updatedMaintenance = maintenanceRepository.save(updatedMaintenance);
		return maintenanceMapper.toResponse(updatedMaintenance);
	}

	@Override
	public boolean deleteMaintenanceById(Long id) {
		if(id <= 0l) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id must be a positive number.");
		}
		
		Optional<Maintenance> fetchedMaintenance = maintenanceRepository.findById(id);
		if(fetchedMaintenance.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Maintenance was not found.");
		}
		
		maintenanceRepository.delete(fetchedMaintenance.get());
		return true;
	}
}
