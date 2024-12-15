package pu.fmi.wsp.hw.carmanagement.web;

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
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseCarDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateCarDTO;
import pu.fmi.wsp.hw.carmanagement.services.CarService;

@RestController
@RequestMapping(
		value = "/cars",
		produces = "application/json")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200"),
		@ApiResponse(responseCode = "400", content = {@Content()})
})
public class CarController {
	private final CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping
	public ResponseEntity<Set<ResponseCarDTO>> getAllCars(
			@RequestParam(value = "carMake", required = false) Optional<String> carMake,
			@RequestParam(value = "garageId", required = false) Optional<Long> garageId,
			@RequestParam(value = "fromYear", required = false) Optional<Integer> fromYear,
			@RequestParam(value = "toYear", required = false) Optional<Integer> toYear) {
		return ResponseEntity.ok(carService.getAllCars(carMake, garageId, fromYear, toYear));
	}
	
	@GetMapping("/{id}")
	@ApiResponse(responseCode = "404", content = {@Content()})
	public ResponseEntity<ResponseCarDTO> getCarById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(carService.getCarById(id));
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<ResponseCarDTO> createCar(
			@Valid @RequestBody CreateCarDTO createCarDTO) {
		return ResponseEntity.ok(carService.createCar(createCarDTO));
	}
	
	@PutMapping(value = "/{id}", consumes = "application/json")
	@ApiResponse(responseCode = "404", content = {@Content()})
	public ResponseEntity<ResponseCarDTO> updateCar(
			@PathVariable("id") Long id,
			@Valid @RequestBody UpdateCarDTO updateCarDTO) {
		return ResponseEntity.ok(carService.updateCar(id, updateCarDTO));
	}
	
	@DeleteMapping("/{id}")
	@ApiResponse(responseCode = "404", content = {@Content()})
	public ResponseEntity<Boolean> deleteCarById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(carService.deleteCarById(id));
	}
}
