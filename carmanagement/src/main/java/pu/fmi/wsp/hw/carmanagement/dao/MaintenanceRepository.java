package pu.fmi.wsp.hw.carmanagement.dao;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pu.fmi.wsp.hw.carmanagement.model.Maintenance;
import pu.fmi.wsp.hw.carmanagement.model.dto.report.MonthlyRequestsReportDTO;

public interface MaintenanceRepository extends CrudRepository<Maintenance, Long> {
	Set<Maintenance> findAll();
	
	/*Applies all filters that are provided. Allows for more flexibility by also working 
	  with only one provided date filter.*/
	@Query("""
			select m from Maintenance m
			where 
				(:carId is null or m.car.id = :carId) and 
				(:garageId is null or m.garage.id = :garageId) and
				(:startDate is null or m.scheduledDate >= :startDate) and 
				(:endDate is null or m.scheduledDate <= :endDate)
			""")
	Set<Maintenance> findAll(
		@Param("carId") Long carId,
		@Param("garageId") Long garageId,
		@Param("startDate") LocalDate startDate,
		@Param("endDate") LocalDate endDate);
	
	@Query("""
			select new pu.fmi.wsp.hw.carmanagement.model.dto.report.MonthlyRequestsReportDTO(year(m.scheduledDate), month(m.scheduledDate), count(m.id)) from Maintenance m 
			where 
				m.garage.id = :garageId and 
				m.scheduledDate >= :startDate and m.scheduledDate <= :endDate 
			group by year(m.scheduledDate), month(m.scheduledDate)
			"""
			)
	Set<MonthlyRequestsReportDTO> getMonthlyRequestsReport(
		@Param("garageId") Long garageId,
		@Param("startDate") LocalDate startDate,
		@Param("endDate") LocalDate endDate);
}
