package com.carparksprint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carparksprint.entity.Customer;
import com.carparksprint.entity.Parking;
import com.carparksprint.entity.ParkingCenter;
import com.carparksprint.exception.CustomException;
import com.carparksprint.exception.DeleteParkingException;
import com.carparksprint.service.CustomerService;

// Can haveapreference to search for near by parking and park the

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/findparkingbylocation/{location}")
	public ResponseEntity<List<ParkingCenter>> allParkingAtLocation(@PathVariable(value="location") String location) throws CustomException
	{
		return new ResponseEntity<List<ParkingCenter>>(customerService.centersAtLocation(location),HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws CustomException
	{
		return new ResponseEntity<Customer>(customerService.addCustomer(customer),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/parkingStatus/{location}/{centerId}")
	public ResponseEntity<String> parkingStatusAtLocation(@PathVariable (value="location") String location,@PathVariable (value="centerId") Long centerId ) throws CustomException
	{
		return new ResponseEntity<String>(customerService.viewParkingAvailable(location,centerId),HttpStatus.OK);
	}
	
	@PostMapping("/bookparking/{centerId}/{customerId}")
	public ResponseEntity<Parking> bookParking(@RequestBody Parking parking , @PathVariable(value="centerId") Long centerId,@PathVariable(value="customerId") Long customerId ) throws CustomException
	{
		return new ResponseEntity<Parking>(customerService.bookParking(parking,centerId,customerId),HttpStatus.OK); 
	}
	
	@DeleteMapping("/deleteparking/{id}")
	public ResponseEntity<String> deleteParkingById(@PathVariable(value = "id") Long id) throws DeleteParkingException
	{
		return new ResponseEntity<String>(customerService.deleteParkingById(id),HttpStatus.OK);
	}
	
	@GetMapping("/viewparking/{id}")
	public ResponseEntity<Parking> viewById(@PathVariable(value = "id") Long id) throws CustomException 
	{
		return new ResponseEntity<Parking>(customerService.viewParkingById(id),HttpStatus.OK);
	}

}
