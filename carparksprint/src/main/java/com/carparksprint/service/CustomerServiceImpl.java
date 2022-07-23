package com.carparksprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carparksprint.entity.Customer;
import com.carparksprint.entity.Parking;
import com.carparksprint.entity.ParkingCenter;
import com.carparksprint.exception.CustomException;
import com.carparksprint.exception.DeleteParkingException;
import com.carparksprint.repository.CustomerRepository;
import com.carparksprint.repository.ParkingCenterRepository;
import com.carparksprint.repository.ParkingRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private ParkingCenterRepository parkingRepo;
	@Autowired
	private ParkingRepository parkRepo;
	
	
	//customer can add his details
	@Override
	public Customer addCustomer(Customer customer) throws CustomException {
		if(customer.getCustomerName().isEmpty() || customer.getVehical().getVehicalLicence().isEmpty()||
				customer.getVehical().getVehicalName().isEmpty()||customer.getVehical().getVehicalType().isEmpty()
			|| customer.getContactNumber()<=0||customer.getCustomerId()<=0 )
		{
			throw new CustomException("Enter Valid Input");
		}
		else
		{
			return customerRepo.save(customer);
		}
		
		
	}

	//customer can view parking available at locations
	@Override
	public List<ParkingCenter> centersAtLocation(String location) throws CustomException {
		if(location.isEmpty())
		{
			throw new CustomException("Enter valid Input");
		}
		else
		{
			List<ParkingCenter> parkingcenters = parkingRepo.centersAtLocation(location); 
			if(parkingcenters.isEmpty())
			{
				throw new CustomException("No Parking Centers At This Location");
			}
			else
			{
				return parkingcenters;
			}
			
		}
		
	}

	//customer can book parking
	@Override
	public Parking bookParking(Parking parking, Long centerId, Long customerId) throws CustomException {
		if(centerId<=0||customerId<=0||parking.getParkingHours()<=0)
		{
			throw new CustomException("Enter Valid Input");
		}
		else
		{
			Optional<ParkingCenter> center = parkingRepo.findById(centerId);
			if(center.get().getBooked()==center.get().getCapacity())
			{
				throw new CustomException("No Parking Space Available At This Center");
			}
			else
			{
				int x = center.get().getBooked();
				center.get().setBooked(x+1);
				int y = parking.getParkingHours();
				parking.setBillAmount((double) (y*70));
				int i = center.get().getCapacity()-center.get().getBooked(); 
				center.get().setAvailableSpot(i);
				if(i>0)
				{
					center.get().setIsAvilable(true);
				}
				else
				{
					center.get().setIsAvilable(false);
				}
			}
			Optional<Customer> customer = customerRepo.findById(customerId);
			parking.setCustomer(customer.get());
			parking.setParkingCenter(center.get());
			return parkRepo.save(parking);
		}
		
	}

	//customer can delete parking
	@Override
	public String deleteParkingById(Long id) throws DeleteParkingException {
		Optional<Parking> parking = parkRepo.findById(id);
		if (parking.isPresent()) {
			int x = parking.get().getParkingCenter().getBooked();
			parking.get().getParkingCenter().setBooked(x-1);
			parkRepo.deleteById(id);
			int i = parking.get().getParkingCenter().getCapacity()-parking.get().getParkingCenter().getBooked(); 
			parking.get().getParkingCenter().setAvailableSpot(i);
			if(i>0)
			{
				parking.get().getParkingCenter().setIsAvilable(true);
			}
			return "Parking Deleted Successfully";
		}else {
			throw new DeleteParkingException("Parking not found");
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
			ParkingCenter parkingcenter =  parkingRepo.centerAvailability(location, centerId); 
			if(parkingcenter==null)
			{throw new CustomException("Parking Center Not Found");}
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
	public Parking viewParkingById(Long id) throws CustomException {
		Optional<Parking> parking = parkRepo.findById(id);
		if(id<=0)
		{	throw new CustomException("Enter Valid Input");}
		else
		{
			if(parking.isPresent())
			{
				
				return parking.get();
			}
			else
			{
				throw new CustomException("Parking Not Found with This Parking Token");
			}
		}
	}

	
	
		
	
}
