package com.carparksprint.service;

import java.util.List;

import com.carparksprint.entity.Parking;
import com.carparksprint.entity.ParkingCenter;
import com.carparksprint.exception.AdminException;
import com.carparksprint.exception.CustomException;
import com.carparksprint.exception.DeleteParkingException;

public interface AdminService {
	
	//add parking 
	// delete parking
	// update parking
	// view parking 
	
	public Parking addParking(Parking parking, Long centerid) throws CustomException;
	
	public String deleteParkingByToken(Long tokenId) throws DeleteParkingException;
	
	public List<Parking> viewAllParking() throws AdminException;
	
	public Parking updateParking(Parking parking, Long id) throws CustomException;
	
	public List<ParkingCenter> viewAllParkingCenter() throws AdminException;
	
	
}
