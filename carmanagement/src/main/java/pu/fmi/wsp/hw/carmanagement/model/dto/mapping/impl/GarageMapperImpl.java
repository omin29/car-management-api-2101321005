package pu.fmi.wsp.hw.carmanagement.model.dto.mapping.impl;

import org.springframework.stereotype.Component;

import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.mapping.GarageMapper;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateGarageDTO;

@Component
public class GarageMapperImpl implements GarageMapper {

	@Override
	public ResponseGarageDTO toResponse(Garage garage) {
		return new ResponseGarageDTO(
				garage.getId(), 
				garage.getName(),
				garage.getLocation(),
				garage.getCity(),
				garage.getCapacity());
	}

	@Override
	public Garage toEntity(CreateGarageDTO createGarageDTO) {
		return new Garage(
				0l,
				createGarageDTO.getName(),
				createGarageDTO.getLocation(),
				createGarageDTO.getCity(),
				createGarageDTO.getCapacity()
				);
	}

	@Override
	public Garage toEntity(UpdateGarageDTO updateGarageDTO, Garage updatedGarage) {
		updatedGarage.setName(updateGarageDTO.getName());
		updatedGarage.setLocation(updateGarageDTO.getLocation());
		updatedGarage.setCity(updateGarageDTO.getCity());
		updatedGarage.setCapacity(updateGarageDTO.getCapacity());
		return updatedGarage;
	}
	
}
