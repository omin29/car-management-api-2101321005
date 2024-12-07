package pu.fmi.wsp.hw.carmanagement.dao;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pu.fmi.wsp.hw.carmanagement.model.Garage;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.GarageDailyAvailabilityReportDTO;

public interface GarageRepository extends CrudRepository<Garage, Long> {
	Set<Garage> findAll();
	Set<Garage> findByCityContainsIgnoreCase(String city);
	
	@Query("""
			select new GarageDailyAvailabilityReportDTO(m.scheduledDate, count(m.id), g.capacity - count(m.id)) from garage g 
			inner join g.maintenances m 
			group by g.id, m.scheduledDate 
			where g.id = :garageId and m.scheduledDate >= :startDate and m.scheduledDate <= :endDate
			""")
	Set<GarageDailyAvailabilityReportDTO> getReport(
		@Param("garageId") Long garageId,
		@Param("startDate") LocalDate startDate,
		@Param("endDate") LocalDate endDate);
}
