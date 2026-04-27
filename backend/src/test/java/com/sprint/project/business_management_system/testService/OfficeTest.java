package com.sprint.project.business_management_system.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;
import jakarta.validation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.sprint.project.business_management_system.Entity.Employee;
import com.sprint.project.business_management_system.Entity.Office;
import com.sprint.project.business_management_system.impl.OfficeServiceImpl;
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.repository.OfficeRepository;
import com.sprint.project.business_management_system.requestDto.OfficeRequestDto;
import com.sprint.project.business_management_system.exception.ResourceNotFoundException;

class OfficeTest {

    @InjectMocks
    private OfficeServiceImpl service;

    @Mock
    private OfficeRepository officeRepo;

    @Mock
    private EmployeeRepository employeeRepo;

    private Office office;
    private Employee employee;
    private Validator validator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        office = new Office();
        office.setOfficeCode("1");
        office.setCity("Chennai");
        office.setCountry("India");

        employee = new Employee();
        employee.setEmployeeNumber(1);
    }

    // -------- SERVICE TEST CASES --------

    @Test
    void testSaveOffice() {
        OfficeRequestDto dto = new OfficeRequestDto();
        dto.setOfficeCode("1");
        when(officeRepo.save(any())).thenReturn(office);
        assertNotNull(service.saveOffice(dto));
    }

    @Test
    void testSaveOfficeVerify() {
        OfficeRequestDto dto = new OfficeRequestDto();
        dto.setOfficeCode("1");
        when(officeRepo.save(any())).thenReturn(office);
        service.saveOffice(dto);
        verify(officeRepo).save(any());
    }

    @Test
    void testGetAllOffices() {
        when(officeRepo.findAll()).thenReturn(List.of(office));
        assertEquals(1, service.getAllOffices().size());
    }

    @Test
    void testGetAllOfficesEmpty() {
        when(officeRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.getAllOffices().isEmpty());
    }

    @Test
    void testGetOfficeById() {
        when(officeRepo.findById("1")).thenReturn(Optional.of(office));
        assertEquals("1", service.getOfficeById("1").getOfficeCode());
    }

    @Test
    void testGetOfficeByIdNotFound() {
        when(officeRepo.findById("1")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getOfficeById("1"));
    }

    @Test
    void testGetEmployeesInOffice() {
        when(employeeRepo.findByOfficeOfficeCode("1"))
                .thenReturn(List.of(employee));

        assertEquals(1, service.getEmployeesInOffice("1").size());
    }

    @Test
    void testDeleteOfficeSuccess() {
        when(officeRepo.existsById("1")).thenReturn(true);
        doNothing().when(officeRepo).deleteById("1");
        assertDoesNotThrow(() -> service.deleteOffice("1"));
        verify(officeRepo).deleteById("1");
    }

    @Test
    void testDeleteOfficeNotFound() {
        when(officeRepo.existsById("1")).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> service.deleteOffice("1"));
    }

    // -------- DTO VALIDATION TEST CASES --------

    @Test
    void testOfficeDtoValid() {
        OfficeRequestDto dto = new OfficeRequestDto();
        dto.setOfficeCode("1");
        dto.setCity("Chennai");
        dto.setPhone("9876543210");
        dto.setAddressLine1("Street");
        dto.setCountry("India");

        assertTrue(validator.validate(dto).isEmpty());
    }

    @Test
    void testOfficeDtoInvalidCodeAlpha() {
        OfficeRequestDto dto = new OfficeRequestDto();
        dto.setOfficeCode("OFFICE1"); // invalid, contains text
        dto.setCity("Chennai");
        dto.setPhone("9876543210");
        dto.setAddressLine1("Street");
        dto.setCountry("India");

        assertFalse(validator.validate(dto).isEmpty());
    }

    @Test
    void testOfficeDtoInvalidPhoneAlpha() {
        OfficeRequestDto dto = new OfficeRequestDto();
        dto.setOfficeCode("1");
        dto.setCity("Chennai");
        dto.setPhone("98765AAAAA"); // invalid, contains text
        dto.setAddressLine1("Street");
        dto.setCountry("India");

        assertFalse(validator.validate(dto).isEmpty());
    }
}