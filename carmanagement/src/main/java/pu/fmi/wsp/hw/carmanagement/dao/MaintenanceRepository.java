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
			select m from maintenance m
			where 
				(:carId is null or m.car_id = :carId) and 
				(:garageId is null or m.garage_id = :garageId) and
				(:startDate is null or m.scheduledDate >= :startDate) and 
				(:endDate is null or m.scheduledDate <= :endDate)
			""")
	Set<Maintenance> findAll(
		@Param("carId") Long carId,
		@Param("garageId") Long garageId,
		@Param("startDate") LocalDate startDate,
		@Param("endDate") LocalDate endDate);
	
	@Query("""
			select new MonthlyRequestsReportDTO(function('YEAR', m.scheduledDate), function('MONTH', m.scheduledDate), count(m.id)) from maintenance m
			group by function('YEAR', m.scheduledDate), function('MONTH', m.scheduledDate) 
			where 
				m.garage_id = :garageId and 
				m.scheduledDate >= :startDate and m.scheduledDate <= :endDate
			"""
			)
	Set<MonthlyRequestsReportDTO> getMonthlyRequestsReport(
		@Param("garageId") Long garageId,
		@Param("startDate") LocalDate startDate,
		@Param("endDate") LocalDate endDate);
}
