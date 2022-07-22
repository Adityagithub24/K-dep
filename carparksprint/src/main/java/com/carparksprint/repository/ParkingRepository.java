package com.carparksprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carparksprint.entity.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

}
