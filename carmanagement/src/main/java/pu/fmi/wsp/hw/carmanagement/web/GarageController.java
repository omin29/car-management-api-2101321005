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
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.GarageDailyAvailabilityReportDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateGarageDTO;
import pu.fmi.wsp.hw.carmanagement.services.GarageService;

@RestController
@RequestMapping(
		value = "/garages",
		produces = "application/json")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200"),
		@ApiResponse(responseCode = "400", content = {@Content()})
})
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
	@ApiResponse(responseCode = "404", content = {@Content()})
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
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<ResponseGarageDTO> createGarage(
			@Valid @RequestBody CreateGarageDTO createGarageDTO) {
		return ResponseEntity.ok(garageService.createGarage(createGarageDTO));
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	@ApiResponse(responseCode = "404", content = {@Content()})
	public ResponseEntity<ResponseGarageDTO> updateGarage(
			@PathVariable("id") Long id,
			@Valid @RequestBody UpdateGarageDTO updateGarageDTO) {
		return ResponseEntity.ok(garageService.updateGarage(id, updateGarageDTO));
	}
	
	@DeleteMapping("/{id}")
	@ApiResponse(responseCode = "404", content = {@Content()})
	public ResponseEntity<Boolean> deleteGarageById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(garageService.deleteGarageById(id));
	}
}
