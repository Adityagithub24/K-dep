package com.carparksprint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.carparksprint.entity.ParkingCenter;



public interface ParkingCenterRepository extends JpaRepository<ParkingCenter, Long> {
	
	@Query("select pc from ParkingCenter pc where pc.centerCity=:centerCity and pc.centerId=:centerId")
	public ParkingCenter centerAvailability(@Param("centerCity") String centerCity , @Param("centerId") Long centerId);
	
	@Query("select pc from ParkingCenter pc where pc.centerCity=:centerCity")
	public List<ParkingCenter> centersAtLocation(@Param("centerCity") String centerCity);

}
