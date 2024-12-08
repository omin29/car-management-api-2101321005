package pu.fmi.wsp.hw.carmanagement.model.dto.mapping;

import pu.fmi.wsp.hw.carmanagement.model.Car;
import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.Maintenance;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateMaintenanceDTO;

public interface MaintenanceMapper {
	ResponseMaintenanceDTO toResponse(Maintenance maintenance);
	Maintenance toEntity(CreateMaintenanceDTO createMaintenanceDTO, Car car, Garage garage);
	Maintenance toEntity(UpdateMaintenanceDTO updateMaintenanceDTO, long maintenanceId, Car car, Garage garage);
}
