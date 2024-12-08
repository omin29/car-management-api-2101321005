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

import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.GarageDailyAvailabilityReportDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateGarageDTO;
import pu.fmi.wsp.hw.carmanagement.services.GarageService;

@RestController
@Validated
@RequestMapping("/garages")
public class GarageController {
	private final GarageService garageService;
	
	public GarageController(GarageService garageService) {
		this.garageService = garageService;
	}
	
	@GetMapping
	public ResponseEntity<Set<ResponseGarageDTO>> getAllGarages(
			@RequestParam(value = "city", required = false) Optional<String> city) {
		return ResponseEntity.ok(garageService.getAllGarages(city));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseGarageDTO> getGarageById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(garageService.getGarageById(id));
	}
	
	@GetMapping("/dailyAvailabilityReport")
	public ResponseEntity<Set<GarageDailyAvailabilityReportDTO>> getGarageReport(
			@RequestParam(value = "garageId", required = true) Long garageId,
			@RequestParam(value = "startDate", required = true) LocalDate startDate,
			@RequestParam(value = "endDate", required = true) LocalDate endDate) {
		return ResponseEntity.ok(garageService.getGarageReport(garageId, startDate, endDate));
	}
	
	@PostMapping
	public ResponseEntity<ResponseGarageDTO> createGarage(@RequestBody CreateGarageDTO createGarageDTO) {
		return ResponseEntity.ok(garageService.createGarage(createGarageDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseGarageDTO> updateGarage(
			@PathVariable("id") Long id,
			@RequestBody UpdateGarageDTO updateGarageDTO) {
		return ResponseEntity.ok(garageService.updateGarage(id, updateGarageDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteGarageById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(garageService.deleteGarageById(id));
	}
}
