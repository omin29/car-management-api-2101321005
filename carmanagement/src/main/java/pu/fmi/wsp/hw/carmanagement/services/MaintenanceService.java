package pu.fmi.wsp.hw.carmanagement.services;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.MonthlyRequestsReportDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateMaintenanceDTO;

public interface MaintenanceService {
	Set<ResponseMaintenanceDTO> getAllMaintenances(
			Optional<Long> carId,
			Optional<Long> garageId,
			Optional<LocalDate> startDate,
			Optional<LocalDate> endDate);
	ResponseMaintenanceDTO getMaintenanceById(Long id);
	Set<MonthlyRequestsReportDTO> getMonthlyRequestsReport(Long garageId, String startMonth, String endMonth);
	ResponseMaintenanceDTO createMaintenance(CreateMaintenanceDTO createMaintenanceDTO);
	ResponseMaintenanceDTO updateMaintenance(Long id, UpdateMaintenanceDTO updateMaintenanceDTO);
	boolean deleteMaintenanceById(Long id);
}
