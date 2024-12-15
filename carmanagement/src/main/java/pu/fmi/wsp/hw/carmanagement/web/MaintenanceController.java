package pu.fmi.wsp.hw.carmanagement.web;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.MonthlyRequestsReportDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.services.MaintenanceService;

@RestController
@RequestMapping(
		value = "/maintenance",
		produces = "application/json")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200"),
		@ApiResponse(responseCode = "400", content = {@Content()})
})
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
	@ApiResponse(responseCode = "404", content = {@Content()})
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
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<ResponseMaintenanceDTO> createMaintenance(
			@Valid @RequestBody CreateMaintenanceDTO createMaintenanceDTO) {
		return ResponseEntity.ok(maintenanceService.createMaintenance(createMaintenanceDTO));
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	@ApiResponse(responseCode = "404", content = {@Content()})
	public ResponseEntity<ResponseMaintenanceDTO> updateMaintenance(
			@PathVariable("id") Long id,
			@Valid @RequestBody UpdateMaintenanceDTO updateMaintenanceDTO) {
		return ResponseEntity.ok(maintenanceService.updateMaintenance(id, updateMaintenanceDTO));
	}
	
	@DeleteMapping("/{id}")
	@ApiResponse(responseCode = "404", content = {@Content()})
	public ResponseEntity<Boolean> deleteMaintenance(@PathVariable("id") Long id) {
		return ResponseEntity.ok(maintenanceService.deleteMaintenanceById(id));
	}
}
