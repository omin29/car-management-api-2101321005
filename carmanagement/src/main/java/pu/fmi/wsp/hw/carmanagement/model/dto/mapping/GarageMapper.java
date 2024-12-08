package pu.fmi.wsp.hw.carmanagement.model.dto.mapping;

import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.dto.create.CreateGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.response.ResponseGarageDTO;
import pu.fmi.wsp.hw.carmanagement.model.dto.update.UpdateGarageDTO;

public interface GarageMapper {
	ResponseGarageDTO toResponse(Garage garage);
	Garage toEntity(CreateGarageDTO createGarageDTO);
	Garage toEntity(UpdateGarageDTO updateGarageDTO, long garageId);
}
