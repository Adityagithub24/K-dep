package com.carparksprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carparksprint.entity.ParkingCenter;
import com.carparksprint.exception.CustomException;
import com.carparksprint.exception.ParkingNotFoundException;
import com.carparksprint.repository.ParkingCenterRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ParkingCenterRepository pcRepo;
	
	@Override
	public ParkingCenter createParkingCenter(ParkingCenter parkingcenter) throws CustomException {
			if(parkingcenter.getBooked()<=0 || parkingcenter.getCapacity()<=0 
					||parkingcenter.getCenterId()<=0 || parkingcenter.getCenterName().isEmpty() || parkingcenter.getCenterCity().isEmpty())
			{
				throw new CustomException("Enter details correctly");
			}
			else
			{
				int x = parkingcenter.getCapacity()-parkingcenter.getBooked(); 
				parkingcenter.setAvailableSpot(x);
				if(x>0)
				{
					parkingcenter.setIsAvilable(true);
				}
				return pcRepo.save(parkingcenter);
			}
		}

	@Override
	public ParkingCenter viewParkingCenter(Long centerId) throws CustomException {
		if(centerId<=0)
		{
			throw new CustomException("Enter Valid Input");
		}
		else
		{
			Optional<ParkingCenter> center = pcRepo.findById(centerId); 
			if(center.isPresent())
			{
				return center.get();
			}
			else
			{
				throw new CustomException("Parking Center Not Found");
			}
		}
		
	}

	@Override
	public String viewParkingAvailable(String location,Long centerId) throws CustomException {
		if(location.isEmpty()||centerId<=0)
		{
				throw new CustomException("Enter Valid Input");
		}
		else
		{
			ParkingCenter parkingcenter =  pcRepo.centerAvailability(location, centerId); 
			if(parkingcenter==null)
			{throw new CustomException("Parking Center Not Available");}
			else
			{
				String str = "Center Name : -"+parkingcenter.getCenterName()+"\n Center Capacity :- "
			                  +parkingcenter.getCapacity()+"\n Spots Booked : - "+parkingcenter.getBooked()
			                  +"\n Spots Available :- "+(parkingcenter.getCapacity()-parkingcenter.getBooked());

				return str;
			}
		}
	}

	@Override
	public List<ParkingCenter> centersAtLocation(String Location) throws CustomException {
		if(Location.isEmpty())
		{
			throw new CustomException("Enter Valid Input");
		}
		else
		{
			List<ParkingCenter> parkingcenters = pcRepo.centersAtLocation(Location); 
			
			if(parkingcenters.isEmpty())
			{
				throw new CustomException("No Parking Centers At this Location");
			}
			else
			{
				return parkingcenters;
			}
		}
		
	}

	@Override
	public String deleteParkingCenterById(Long Id) throws CustomException {
		
		if(Id<=0)
		{
			throw new CustomException("Enter Valid Input");
		}
		else
		{
			Optional<ParkingCenter> parkingcenter = pcRepo.findById(Id);
			if(parkingcenter.isPresent())
			{
				pcRepo.deleteById(Id);
				return "Parking center Deleted Successfully";
			}
			else
			throw new CustomException("parking center not found with Id : - "+ Id);
		}
		
	}

	@Override
	public ParkingCenter updateParkingCenter(ParkingCenter parkingcenter, Long Id) throws ParkingNotFoundException {
		
		if(parkingcenter.getCenterName().isEmpty()||parkingcenter.getCenterCity().isEmpty()||parkingcenter.getBooked()<=0
				||parkingcenter.getCapacity()<=0||parkingcenter.getCenterId()<=0||Id<=0)
		{
			throw new ParkingNotFoundException("Enter Valid Input");
		}
		else
		{
			Optional<ParkingCenter> pcenter = pcRepo.findById(Id);
			if(pcenter.isPresent())
			{
				return pcRepo.save(parkingcenter);
			}
			else
			{
				throw new ParkingNotFoundException("center with this Id not Found");
			}
		}
		
		
		
	}

}




























