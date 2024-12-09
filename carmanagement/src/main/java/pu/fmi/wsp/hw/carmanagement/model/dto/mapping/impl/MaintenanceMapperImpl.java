package pu.fmi.wsp.hw.carmanagement.model.dto.mapping.impl;

import org.springframework.stereotype.Component;

import pu.fmi.wsp.hw.carmanagement.model.Car;
import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.Maintenance;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.mapping.MaintenanceMapper;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseMaintenanceDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateMaintenanceDTO;

@Component
public class MaintenanceMapperImpl implements MaintenanceMapper {

	@Override
	public ResponseMaintenanceDTO toResponse(Maintenance maintenance) {
		return new ResponseMaintenanceDTO(
				maintenance.getId(),
				maintenance.getCar().getId(),
				String.format("%s %s", maintenance.getCar().getMake(), maintenance.getCar().getModel()),
				maintenance.getServiceType(),
				maintenance.getScheduledDate(),
				maintenance.getGarage().getId(),
				maintenance.getGarage().getName()
				);
	}

	@Override
	public Maintenance toEntity(CreateMaintenanceDTO createMaintenanceDTO, Car car, Garage garage) {
		Maintenance newMaintenance = new Maintenance(
				null,
				createMaintenanceDTO.getServiceType(),
				createMaintenanceDTO.getScheduledDate()
				);
		newMaintenance.setCar(car);
		newMaintenance.setGarage(garage);
		return newMaintenance;
	}

	@Override
	public Maintenance toEntity(UpdateMaintenanceDTO updateMaintenanceDTO, Maintenance updatedMaintenance, Car car, Garage garage) {
		updatedMaintenance.setServiceType(updateMaintenanceDTO.getServiceType());
		updatedMaintenance.setScheduledDate(updateMaintenanceDTO.getScheduledDate());
		updatedMaintenance.setCar(car);
		updatedMaintenance.setGarage(garage);
		return updatedMaintenance;
	}

}
