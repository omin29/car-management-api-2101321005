package pu.fmi.wsp.hw.carmanagement.web;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.MonthlyRequestsReportDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.services.MaintenanceService;

@RestController
@Validated
@RequestMapping("/maintenance")
public class MaintenanceController {
	private final MaintenanceService maintenanceService;
	
	public MaintenanceController(MaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}
	
	@GetMapping
	public ResponseEntity<Set<ResponseMaintenanceDTO>> getAllMaintenances(
			@RequestParam(value = "carId", required = false) Optional<Long> carId,
			@RequestParam(value = "garageId", required = false) Optional<Long> garageId,
			@RequestParam(value = "startDate", required = false) Optional<LocalDate> startDate,
			@RequestParam(value = "endDate", required = false) Optional<LocalDate> endDate) {
		return ResponseEntity.ok(maintenanceService.getAllMaintenances(carId, garageId, startDate, endDate));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseMaintenanceDTO> getMaintenanceById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));
	}
	
	@GetMapping("/monthlyRequestsReport")
	public ResponseEntity<Set<MonthlyRequestsReportDTO>> monthlyRequestsReport(
			@RequestParam(value = "garageId", required = true) Long garageId,
			@RequestParam(value = "startMonth", required = true) String startMonth,
			@RequestParam(value = "endMonth", required = true) String endMonth) {
		return ResponseEntity.ok(maintenanceService.getMonthlyRequestsReport(garageId, startMonth, endMonth));
	}
	
	@PostMapping
	public ResponseEntity<ResponseMaintenanceDTO> createMaintenance(
			@RequestBody CreateMaintenanceDTO createMaintenanceDTO) {
		return ResponseEntity.ok(maintenanceService.createMaintenance(createMaintenanceDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseMaintenanceDTO> updateMaintenance(
			@PathVariable("id") Long id,
			@RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
		return ResponseEntity.ok(maintenanceService.updateMaintenance(id, updateMaintenanceDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteMaintenance(@PathVariable("id") Long id) {
		return ResponseEntity.ok(maintenanceService.deleteMaintenanceById(id));
	}
}
