package com.carparksprint.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
public class ParkingCenter {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Long   centerId;
	private String centerName;
	private String centerCity;
	private Integer capacity;
	private Integer booked;
	
	@JsonIgnore
	private Integer availableSpot;
	@JsonIgnore
	private Boolean isAvilable;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY,mappedBy="parkingCenter")
	private List<Parking> parking = new ArrayList<>();

	
	public Integer getBooked() {
		return booked;
	}

	public void setBooked(Integer booked) {
		this.booked = booked;
	}

	public String getCenterCity() {
		return centerCity;
	}

	public void setCenterCity(String centerCity) {
		this.centerCity = centerCity;
	}

	public Boolean getIsAvilable() {
		return isAvilable;
	}

	public void setIsAvilable(Boolean isAvilable) {
		this.isAvilable = isAvilable;
	}

	
	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<Parking> getParking() {
		return parking;
	}

	public void setParking(List<Parking> parking) {
		this.parking = parking;
	}
	
	


	public Integer getAvailableSpot() {
		return availableSpot;
	}

	public void setAvailableSpot(Integer availableSpot) {
		this.availableSpot = availableSpot;
	}

	@Override
	public String toString() {
		return "ParkingCenter [centerId=" + centerId + ", centerName=" + centerName + ", centerCity=" + centerCity
				+ ", capacity=" + capacity + ", booked=" + booked + ", availableSpot=" + availableSpot + ", isAvilable="
				+ isAvilable + ", parking=" + parking + "]";
	}


}
