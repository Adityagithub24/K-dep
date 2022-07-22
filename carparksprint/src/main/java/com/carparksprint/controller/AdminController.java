package com.carparksprint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carparksprint.entity.Parking;
import com.carparksprint.entity.ParkingCenter;
import com.carparksprint.exception.AdminException;
import com.carparksprint.exception.CustomException;
import com.carparksprint.exception.DeleteParkingException;
import com.carparksprint.service.AdminService;

// Add/Edit/Delete/View parking details.

@RestController
@RequestMapping("Admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> check()
	{
		return new ResponseEntity<String>("hi hello",HttpStatus.ACCEPTED);
	}
	
	//view All parkings
	@GetMapping("/allparking")
	public ResponseEntity<List<Parking>> getAllParking() throws AdminException
	{
		return new ResponseEntity<List<Parking>>(adminService.viewAllParking(),HttpStatus.OK);
	}
	
	@GetMapping("/allparkingcenter")
	public ResponseEntity<List<ParkingCenter>> getAllParkingCenter() throws AdminException
	{
		return new ResponseEntity<List<ParkingCenter>>(adminService.viewAllParkingCenter(),HttpStatus.OK);
	}
	
	//Add A New Parking
	@PostMapping("/addparking/{centerId}")
	public ResponseEntity<Parking> addParking(@RequestBody Parking parking ,@PathVariable(value="centerId") Long centerId) throws CustomException
	{
		return new ResponseEntity<Parking>(adminService.addParking(parking,centerId),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteparking/{id}")
	public ResponseEntity<String> deleteParking(@PathVariable(value="id") Long id) throws DeleteParkingException
	{
		return new ResponseEntity<String>(adminService.deleteParkingByToken(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateparking/{id}")
	public ResponseEntity<Parking> updateParking(@RequestBody Parking parking ,@PathVariable(value="id") Long id) throws CustomException
	{
		return new ResponseEntity<Parking>(adminService.updateParking(parking,id),HttpStatus.OK); 
	}
	
}
