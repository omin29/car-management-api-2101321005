package pu.fmi.wsp.hw.carmanagement.services;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.GarageDailyAvailabilityReportDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateGarageDTO;

public interface GarageService {
	Set<ResponseGarageDTO> getAllGarages(Optional<String> city);
	ResponseGarageDTO getGarageById(Long id);
	Set<GarageDailyAvailabilityReportDTO> getGarageReport(Long garageId, LocalDate startDate, LocalDate endDate);
	ResponseGarageDTO createGarage(CreateGarageDTO createGarageDTO);
	ResponseGarageDTO updateGarage(Long id, UpdateGarageDTO updateGarageDTO);
	boolean deleteGarageById(Long id);
}
