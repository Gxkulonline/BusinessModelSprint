package com.sprint.project.business_management_system.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.Entity.Office;
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.repository.OfficeRepository;
import com.sprint.project.business_management_system.requestDto.EmployeeRequestDto;
import com.sprint.project.business_management_system.requestDto.EmployeeRequestDto.OfficeDto;
import com.sprint.project.business_management_system.responseDto.EmployeeResponseDto;
import com.sprint.project.business_management_system.service.EmployeeServiceImpl;

class EmployeeTest {

    @InjectMocks
    private EmployeeServiceImpl service;

    @Mock
    private EmployeeRepository employeeRepo;

    @Mock
    private OfficeRepository officeRepo;

    private Employee employee;
    private Office office;
    private EmployeeRequestDto dto;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        // Sample Office
        office = new Office();
        office.setOfficeCode("OF1");

        // Sample Employee
        employee = new Employee();
        employee.setEmployeeNumber(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setJobTitle("Manager");

        // DTO setup
        dto = new EmployeeRequestDto();
        dto.setEmployeeNumber(1);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setJobTitle("Manager");

        OfficeDto officeDto = new OfficeDto();
        officeDto.setOfficeCode("OF1");
        dto.setOffice(officeDto);
    }

    // -------- 10 IMPORTANT TEST CASES --------

    // 1. Get all employees - success
    @Test
    void testGetAllEmployees() {
        when(employeeRepo.findAll()).thenReturn(List.of(employee));
        assertEquals(1, service.getAll().size());
    }

    // 2. Get all employees - empty
    @Test
    void testGetAllEmployeesEmpty() {
        when(employeeRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.getAll().isEmpty());
    }

    // 3. Get employee by ID - success
    @Test
    void testGetEmployeeById() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        assertEquals("John", service.getById(1).getFirstName());
    }

    // 4. Get employee by ID - not found
    @Test
    void testGetEmployeeByIdNotFound() {
        when(employeeRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> service.getById(1));
    }

    // 5. Save employee - basic
    @Test
    void testSaveEmployee() {
        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));
        when(employeeRepo.save(any())).thenReturn(employee);

        assertNotNull(service.save(dto));
    }

    // 6. Save employee - verify repo call
    @Test
    void testSaveEmployeeVerify() {
        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));
        when(employeeRepo.save(any())).thenReturn(employee);

        service.save(dto);
        verify(employeeRepo).save(any());
    }

    // 7. Save employee - office not found
    @Test
    void testSaveEmployeeOfficeNotFound() {
        when(officeRepo.findById("OF1")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.save(dto));
    }

    // 8. Save employee with manager - success
    @Test
    void testSaveEmployeeWithManager() {
        dto.setReportsTo(2);

        Employee manager = new Employee();
        manager.setEmployeeNumber(2);

        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));
        when(employeeRepo.findById(2)).thenReturn(Optional.of(manager));
        when(employeeRepo.save(any())).thenReturn(employee);

        assertNotNull(service.save(dto));
    }

    // 9. Save employee - manager not found
    @Test
    void testSaveEmployeeManagerNotFound() {
        dto.setReportsTo(2);

        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));
        when(employeeRepo.findById(2)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.save(dto));
    }

    // 10. Save employee - check mapping correctness
    @Test
    void testSaveEmployeeDataMapping() {
        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));
        when(employeeRepo.save(any())).thenReturn(employee);

        EmployeeResponseDto result = service.save(dto);

        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("Manager", result.getJobTitle());
    }
}