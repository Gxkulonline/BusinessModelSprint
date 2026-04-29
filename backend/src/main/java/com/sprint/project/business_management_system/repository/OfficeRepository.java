package com.sprint.project.business_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.project.business_management_system.entity.Employee;
import com.sprint.project.business_management_system.entity.Office;

public interface OfficeRepository extends JpaRepository<Office, String> {
	@Query("SELECT o FROM Office o WHERE o.city= :city")
	List<Office> findByCity(String city);
	List<Office> findByCountry(String country);
	
}
