package com.sprint.project.business_management_system.service;
import java.util.List;
import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.requestDto.OfficeRequestDto;
import com.sprint.project.business_management_system.responseDto.OfficeResponseDto;

public interface OfficeService {
    OfficeResponseDto saveOffice(OfficeRequestDto officeDto);
    List<OfficeResponseDto> getAllOffices();
    OfficeResponseDto getOfficeById(String id);
    List<Employee> getEmployeesInOffice(String officeCode);
    void deleteOffice(String id);
}
