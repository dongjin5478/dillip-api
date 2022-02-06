package com.student.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.api.entity.ReportEntity;

public interface ReportEntityRepository extends JpaRepository<ReportEntity, Integer> {
	public List<ReportEntity> findByVehicleNumber(String vehicleNumber);
}