package com.carparksprint.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * type of vehicle 
 * model 
 * company
 * vechile number
 *  
 * */

@Entity
public class Vehical {

	@Id
	private String vehicalLicence;
	private String vehicalName;
	private String vehicalType;
	
	@OneToOne(cascade = CascadeType.ALL , mappedBy="vehical")
	//@JoinColumn(name = "customer_Id",referencedColumnName = "customerId")
	//@MappedBy()
	@JsonIgnore
	private Customer customer;
	

//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
//	private Parking parking;

	public String getVehicalLicence() {
		return vehicalLicence;
	}

	public void setVehicalLicence(String vehicalLicence) {
		this.vehicalLicence = vehicalLicence;
	}

	public String getVehicalName() {
		return vehicalName;
	}

	public void setVehicalName(String vehicalName) {
		this.vehicalName = vehicalName;
	}

	public String getVehicalType() {
		return vehicalType;
	}

	public void setVehicalType(String vehicalType) {
		this.vehicalType = vehicalType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Vehical [vehicalLicence=" + vehicalLicence + ", vehicalName=" + vehicalName + ", vehicalSize=" + ", vehicalType=" + vehicalType + ", customer=" + customer + "]";
	}

	
	
}
