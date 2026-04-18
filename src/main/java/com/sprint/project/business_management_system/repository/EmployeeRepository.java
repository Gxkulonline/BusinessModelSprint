
package com.sprint.project.business_management_system.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.project.business_management_system.entity.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByManagerEmployeeNumber(Integer managerId);
	List<Employee> findByOfficeOfficeCode(String officeCode);
//	 @Query("SELECT e FROM Employee e WHERE e.reportsTo IS NULL")
//	    List<Employee> findManagers();
}