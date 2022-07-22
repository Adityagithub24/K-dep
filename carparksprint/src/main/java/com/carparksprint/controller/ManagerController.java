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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carparksprint.entity.ParkingCenter;
import com.carparksprint.exception.CustomException;
import com.carparksprint.exception.ParkingNotFoundException;
import com.carparksprint.service.ManagerService;

// who will manage parking with allocation,availability and billing etc


@RestController
@RequestMapping("manager")
public class ManagerController {

	@Autowired 
	private ManagerService managerService;
	
	@PostMapping("/addcenter")
	public ResponseEntity<ParkingCenter> addParkingCenter(@RequestBody ParkingCenter parkingcenter) throws CustomException
	{
		return new ResponseEntity<ParkingCenter>(managerService.createParkingCenter(parkingcenter),HttpStatus.OK);
	}
	
	@GetMapping("/findCenterById")
	public ResponseEntity<ParkingCenter> findParkingCenterById(@RequestParam Long centerId) throws CustomException {
		return new ResponseEntity<ParkingCenter>(managerService.viewParkingCenter(centerId),HttpStatus.OK);
	}
	
	@GetMapping("/parkingStatus/{location}/{centerId}")
	public ResponseEntity<String> parkingStatusAtLocation(@PathVariable (value="location") String location,@PathVariable (value="centerId") Long centerId ) throws CustomException
	{
		return new ResponseEntity<String>(managerService.viewParkingAvailable(location,centerId),HttpStatus.OK);
	}
	
	@GetMapping("/parkingcentersatlocation")
	public ResponseEntity<List<ParkingCenter>> allParkingAtLocation(@RequestParam String location) throws CustomException
	{
		return new ResponseEntity<List<ParkingCenter>>(managerService.centersAtLocation(location),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecenetrbyid/{id}")
	public ResponseEntity<String> deleteCenterbyId(@PathVariable (value="id") Long id) throws CustomException
	{
		return new ResponseEntity<String>(managerService.deleteParkingCenterById(id),HttpStatus.OK);
	}
	
	@PutMapping("/updatecenterbyId/{id}")
	public ResponseEntity<ParkingCenter> updateCenterbyId(@RequestBody ParkingCenter parkingcenter,@PathVariable(value="id")Long id) throws ParkingNotFoundException
	{
		return new ResponseEntity<ParkingCenter>(managerService.updateParkingCenter(parkingcenter, id),HttpStatus.OK);
	}
	
	
}
