package com.carparksprint.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.springframework.stereotype.Component;

/*
 * parking details 
 * no of vehicals
 * types of vehicals
 * space availabilty
 * allocation
 * availability
 * billing
 * 
 * 
 * */
@Component
@Entity
public class Parking {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long tokenId;
	private Integer parkingHours;
	
	private Double  billAmount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "center_Id",referencedColumnName = "centerId")
	 private ParkingCenter parkingCenter;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_Id",referencedColumnName = "customerId")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private Customer customer;
//	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "vehical_Id",referencedColumnName = "vehicalId")
//	private Vehical vehical;
//	
	
	public Long getTokenId() {
		return tokenId;
	}

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public ParkingCenter getParkingCenter() {
		return parkingCenter;
	}

	public void setParkingCenter(ParkingCenter parkingCenter) {
		this.parkingCenter = parkingCenter;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
//	public Vehical getVehical() {
//		return vehical;
//	}
//
//	public void setVehical(Vehical vehical) {
//		this.vehical = vehical;
//	}

	public Integer getParkingHours() {
		return parkingHours;
	}

	public void setParkingHours(Integer parkingHours) {
		this.parkingHours = parkingHours;
	}

	@Override
	public String toString() {
		return "Parking [tokenId=" + tokenId + ", parkingHours=" + parkingHours + ", parkingCenter=" + parkingCenter
				+ ", customer=" + customer + "]";
	}

	
	
}
