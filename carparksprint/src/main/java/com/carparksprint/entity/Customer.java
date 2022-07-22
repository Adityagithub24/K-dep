package com.carparksprint.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * persnol Details
 * vechile detaisl
 * parking details
 * 
 * */
@Component
@Entity
@Table(name="Customer_Table")
public class Customer {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Long customerId;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="contact number")
	private Long contactNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vehical_Idk",referencedColumnName = "vehicalLicence")
	private Vehical vehical;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL , mappedBy="customer")
	//@JoinColumn(name = "token_Id",referencedColumnName = "tokenId")
	private Parking parking;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Vehical getVehical() {
		return vehical;
	}

	public void setVehical(Vehical vehical) {
		this.vehical = vehical;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", contactNumber="
				+ contactNumber + ", vehical=" + vehical + ", parking=" + parking + "]";
	}
	
	
	
}
