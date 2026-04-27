package com.sprint.project.business_management_system.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;
import jakarta.validation.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

import com.sprint.project.business_management_system.Entity.*;
import com.sprint.project.business_management_system.impl.EmployeeServiceImpl;
import com.sprint.project.business_management_system.repository.*;
import com.sprint.project.business_management_system.requestDto.EmployeeRequestDto;
import com.sprint.project.business_management_system.requestDto.EmployeeRequestDto.OfficeDto;
import com.sprint.project.business_management_system.exception.ResourceNotFoundException;

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
    private Validator validator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        validator = Validation.buildDefaultValidatorFactory().getValidator();

        office = new Office();
        office.setOfficeCode("OF1");

        employee = new Employee();
        employee.setEmployeeNumber(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");

        dto = new EmployeeRequestDto();
        dto.setEmployeeNumber(1);
        dto.setFirstName("John");
        dto.setLastName("Doe");

        OfficeDto o = new OfficeDto();
        o.setOfficeCode("OF1");
        dto.setOffice(o);
    }

    @Test void t1_getAll() {
        when(employeeRepo.findAll()).thenReturn(List.of(employee));
        assertEquals(1, service.getAll().size());
    }

    @Test void t2_getById() {
        when(employeeRepo.findById(1)).thenReturn(Optional.of(employee));
        assertEquals("John", service.getById(1).getFirstName());
    }

    @Test void t3_notFound() {
        when(employeeRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getById(1));
    }

    @Test void t4_save() {
        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));
        when(employeeRepo.save(any())).thenReturn(employee);
        assertNotNull(service.save(dto));
    }

    @Test void t5_saveVerify() {
        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));
        when(employeeRepo.save(any())).thenReturn(employee);
        service.save(dto);
        verify(employeeRepo).save(any());
    }

    @Test void t6_officeFail() {
        when(officeRepo.findById("OF1")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.save(dto));
    }

    @Test void t7_managerSuccess() {
        dto.setReportsTo(2);
        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));
        when(employeeRepo.findById(2)).thenReturn(Optional.of(new Employee()));
        when(employeeRepo.save(any())).thenReturn(employee);

        assertNotNull(service.save(dto));
    }

    @Test void t8_managerFail() {
        dto.setReportsTo(2);
        when(employeeRepo.findById(2)).thenReturn(Optional.empty());
        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));

        assertThrows(ResourceNotFoundException.class, () -> service.save(dto));
    }

    @Test void t9_mapping() {
        when(employeeRepo.findAll()).thenReturn(List.of(employee));
        assertEquals("John", service.getAll().get(0).getFirstName());
    }

    @Test void t10_verifyFindAll() {
        when(employeeRepo.findAll()).thenReturn(List.of(employee));
        service.getAll();
        verify(employeeRepo).findAll();
    }

  

    @Test void t12_invalidName() {
        dto.setFirstName("");
        assertFalse(validator.validate(dto).isEmpty());
    }

    @Test void t13_invalidOffice() {
        dto.setOffice(null);
        assertFalse(validator.validate(dto).isEmpty());
    }
}