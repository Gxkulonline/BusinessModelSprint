package com.sprint.project.business_management_system.service;
import java.util.List;

import com.sprint.project.business_management_system.entity.Employee;
import com.sprint.project.business_management_system.entity.Office;
public interface OfficeService {
	  Office saveOffice(Office office);
	    List<Office> getAllOffices();
	    Office getOfficeById(String id);
	    List<Employee> getEmployeesInOffice(String officeCode);
}
