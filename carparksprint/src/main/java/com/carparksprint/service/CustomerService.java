package com.carparksprint.service;

import java.util.List;

import com.carparksprint.entity.Customer;
import com.carparksprint.entity.Parking;
import com.carparksprint.entity.ParkingCenter;
import com.carparksprint.exception.CustomException;
import com.carparksprint.exception.DeleteParkingException;

public interface CustomerService {
	
	//bookparking-at location
	//search parking- by location 
	
	public Customer addCustomer(Customer customer) throws CustomException;

	public List<ParkingCenter> centersAtLocation(String location) throws CustomException;
	
	public Parking bookParking(Parking parking, Long centerId, Long customerId) throws CustomException;
	
	public String deleteParkingById(Long id) throws DeleteParkingException;

	public String viewParkingAvailable(String location,Long centerId) throws CustomException;

	public Parking viewParkingById(Long id) throws CustomException;
	
	//update his parking

}
