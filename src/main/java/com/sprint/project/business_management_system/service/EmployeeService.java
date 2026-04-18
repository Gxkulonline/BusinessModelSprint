package com.sprint.project.business_management_system.service;

import java.util.List;

import com.sprint.project.business_management_system.requestDto.EmployeeRequestDto;
import com.sprint.project.business_management_system.responseDto.EmployeeResponseDto;
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
