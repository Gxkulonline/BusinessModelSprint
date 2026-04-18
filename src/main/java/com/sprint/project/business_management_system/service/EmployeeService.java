package com.sprint.project.business_management_system.service;

import java.util.List;
<<<<<<< HEAD

import com.sprint.project.business_management_system.entity.Employee;
=======
import com.sprint.project.business_management_system.requestDto.EmployeeRequestDto;
import com.sprint.project.business_management_system.responseDto.EmployeeResponseDto;

>>>>>>> refs/heads/master
public interface EmployeeService {

    List<EmployeeResponseDto> getAll();

    EmployeeResponseDto getById(Integer id);

    EmployeeResponseDto save(EmployeeRequestDto dto);
}
//public interface EmployeeService {
//    Employee saveEmployee(Employee e);
//    List<Employee> getAllEmployees();
//    Employee getEmployeeById(Integer id);
//    Employee getManager(Integer id);
//    List<Employee> getSubordinates(Integer id);
//}
