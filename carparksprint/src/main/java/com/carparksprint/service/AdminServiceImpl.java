package com.carparksprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carparksprint.entity.Parking;
import com.carparksprint.entity.ParkingCenter;
import com.carparksprint.exception.AdminException;
import com.carparksprint.exception.CustomException;
import com.carparksprint.exception.DeleteParkingException;
import com.carparksprint.repository.CustomerRepository;
import com.carparksprint.repository.ParkingCenterRepository;
import com.carparksprint.repository.ParkingRepository;
import com.carparksprint.repository.VehicalRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private ParkingRepository parkingRepos;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private VehicalRepository vehicalRepo;
	@Autowired
	private ParkingCenterRepository pcRepo;

	

	@Override
	public String deleteParkingByToken(Long tokenId) throws DeleteParkingException {
		if (tokenId <= 0) {
			throw new DeleteParkingException("Enter Valid Input");
		} else {
			Optional<Parking> parking = parkingRepos.findById(tokenId);
			if (parking.isPresent()) {
				int x = parking.get().getParkingCenter().getBooked();
				parking.get().getParkingCenter().setBooked(x - 1);
				parkingRepos.deleteById(tokenId);
				int i = parking.get().getParkingCenter().getCapacity() - parking.get().getParkingCenter().getBooked();
				parking.get().getParkingCenter().setAvailableSpot(i);
				if (i > 0) {
					parking.get().getParkingCenter().setIsAvilable(true);
				}
				return "Parking Deleted Successfully";
			} else {
				throw new DeleteParkingException("Parking not found");
			}
		}

	}

	@Override
	public List<Parking> viewAllParking() throws AdminException {
		if (parkingRepos.findAll().isEmpty()) {
			throw new AdminException("No Parkings available");
		} else {
			return parkingRepos.findAll();
		}

	}

	@Override
	public Parking updateParking(Parking parking, Long id) throws CustomException {
		if(parking.getCustomer().getContactNumber() <= 0 || parking.getCustomer().getCustomerId() <= 0
				|| parking.getCustomer().getCustomerName().isEmpty()
				|| parking.getCustomer().getParking().getParkingHours() <= 0||id<=0)
		{
			throw new CustomException("Enter Valid Input");
		}
		Optional<Parking> getparking = parkingRepos.findById(id);
		if (getparking.isPresent()) {
			return parkingRepos.save(parking);
		} else {
			throw new CustomException("Parking not found with this id to update");
			
		}

	}

	@Override
	public List<ParkingCenter> viewAllParkingCenter() throws AdminException {
		if (pcRepo.findAll().isEmpty()) {
			throw new AdminException("No Parkings Centers available");
		} else {
			return pcRepo.findAll();
		}

	}
	
//	@Override
//	public Parking addParking(Parking parking, Long centerId) throws CustomException {
//
//		if (centerId <= 0 || parking.getCustomer().getContactNumber() <= 0 || parking.getCustomer().getCustomerId() <= 0
//				|| parking.getCustomer().getCustomerName().isEmpty()
//				|| parking.getCustomer().getParking().getParkingHours() <= 0)
//		{
//			throw new CustomException("Enter Valid Input");
//		} 
//		else 
//		{
//			Optional<ParkingCenter> pc = pRepo.findById(centerId);
//			if (pc.isEmpty())
//			{
//				throw new CustomException("Parking Center Dose Not Exists");
//			} 
//			else
//			{
//				int y = pc.get().getBooked();
//				pc.get().setBooked(y + 1);
//				int z = parking.getParkingHours();
//				parking.setBillAmount((double) (z * 70));
//				int i = pc.get().getCapacity() - pc.get().getBooked();
//				pc.get().setAvailableSpot(i);
//				if (i > 0) {
//					pc.get().setIsAvilable(true);
//				} else {
//					pc.get().setIsAvilable(false);
//				}
//				parking.setParkingCenter(pc.get());
//				return parkingRepos.save(parking);
//			}
//
//		}
//	}

	@Override
	public Parking addParking(Parking parking, Long centerid) throws CustomException {

		Optional<ParkingCenter> parkingcenter = pcRepo.findById(centerid);
		if(parkingcenter.get().getCenterId()==centerid)
		{
			int y = parkingcenter.get().getBooked();
			parkingcenter.get().setBooked(y + 1);
			int z = parking.getParkingHours();
			parking.setBillAmount((double) (z * 70));
			int i = parkingcenter.get().getCapacity() - parkingcenter.get().getBooked();
			parkingcenter.get().setAvailableSpot(i);
			if (i > 0) {
				parkingcenter.get().setIsAvilable(true);
			} else {
				parkingcenter.get().setIsAvilable(false);
			}
			parking.setParkingCenter(parkingcenter.get());
			return parkingRepos.save(parking);
		}
		else
		{
			throw new CustomException("Center not available");
		}
 		
		
	}

}
