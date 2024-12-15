package pu.fmi.wsp.hw.carmanagement.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pu.fmi.wsp.hw.carmanagement.model.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
	Set<Car> findAll();
	
	//If there are any provided filters, a result will be returned if at least one of them is satisfied.
	@Query("""
			select c from Car c 
			left join c.garages g 
			where 
				(:make is null and :garageId is null and :fromYear is null and :toYear is null) or 
				(:make is not null and lower(c.make) like lower(concat('%', :make, '%'))) or 
				(:garageId is not null and g.id = :garageId) or 
				(:fromYear is not null and :toYear is not null and 
					c.productionYear >= :fromYear and c.productionYear <= :toYear)
			""")
	Set<Car> findAll(
		@Param("make") String make, 
		@Param("garageId") Long garageId,
		@Param("fromYear") Integer fromYear,
		@Param("toYear") Integer toYear);
}
