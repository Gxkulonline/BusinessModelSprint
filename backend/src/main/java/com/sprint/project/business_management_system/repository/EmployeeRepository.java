
package com.sprint.project.business_management_system.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprint.project.business_management_system.Entity.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query("SELECT e FROM Employee e WHERE e.manager.employeeNumber = :managerId")
	List<Employee> findByManagerEmployeeNumber(Integer managerId);
	List<Employee> findByOfficeOfficeCode(String officeCode);
}