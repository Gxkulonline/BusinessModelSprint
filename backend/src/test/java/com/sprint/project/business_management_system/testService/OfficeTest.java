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
import com.sprint.project.business_management_system.repository.EmployeeRepository;
import com.sprint.project.business_management_system.repository.OfficeRepository;
import com.sprint.project.business_management_system.requestDto.OfficeRequestDto;
import com.sprint.project.business_management_system.service.OfficeServiceImpl;

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
        office.setOfficeCode("OF1");
        office.setCity("Chennai");
        office.setCountry("India");

        employee = new Employee();
        employee.setEmployeeNumber(1);
    }

    // -------- SERVICE TEST CASES --------

    // 1. Save office - success
    @Test
    void testSaveOffice() {
        when(officeRepo.save(any())).thenReturn(office);
        assertNotNull(service.saveOffice(office));
    }

    // 2. Save office - verify call
    @Test
    void testSaveOfficeVerify() {
        when(officeRepo.save(any())).thenReturn(office);
        service.saveOffice(office);
        verify(officeRepo).save(office);
    }

    // 3. Get all offices - success
    @Test
    void testGetAllOffices() {
        when(officeRepo.findAll()).thenReturn(List.of(office));
        assertEquals(1, service.getAllOffices().size());
    }

    // 4. Get all offices - empty
    @Test
    void testGetAllOfficesEmpty() {
        when(officeRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(service.getAllOffices().isEmpty());
    }

    // 5. Get office by ID - found
    @Test
    void testGetOfficeById() {
        when(officeRepo.findById("OF1")).thenReturn(Optional.of(office));
        assertEquals("OF1", service.getOfficeById("OF1").getOfficeCode());
    }

    // 6. Get office by ID - not found
    @Test
    void testGetOfficeByIdNotFound() {
        when(officeRepo.findById("OF1")).thenReturn(Optional.empty());
        assertNull(service.getOfficeById("OF1"));
    }

    // 7. Get employees in office - success
    @Test
    void testGetEmployeesInOffice() {
        when(employeeRepo.findByOfficeOfficeCode("OF1"))
                .thenReturn(List.of(employee));

        assertEquals(1, service.getEmployeesInOffice("OF1").size());
    }

    // 8. Get employees in office - empty
    @Test
    void testGetEmployeesInOfficeEmpty() {
        when(employeeRepo.findByOfficeOfficeCode("OF1"))
                .thenReturn(new ArrayList<>());

        assertTrue(service.getEmployeesInOffice("OF1").isEmpty());
    }

    // 9. Verify employee repo call
    @Test
    void testEmployeeRepoVerify() {
        when(employeeRepo.findByOfficeOfficeCode("OF1"))
                .thenReturn(List.of(employee));

        service.getEmployeesInOffice("OF1");
        verify(employeeRepo).findByOfficeOfficeCode("OF1");
    }

    // 10. Verify office repo findAll call
    @Test
    void testOfficeRepoFindAllVerify() {
        when(officeRepo.findAll()).thenReturn(List.of(office));

        service.getAllOffices();
        verify(officeRepo).findAll();
    }

    // -------- DTO VALIDATION TEST CASES --------

    // 11. Valid DTO
    @Test
    void testOfficeDtoValid() {
        OfficeRequestDto dto = new OfficeRequestDto();
        dto.setOfficeCode("OF1");
        dto.setCity("Chennai");
        dto.setPhone("9876543210");
        dto.setAddressLine1("Street");
        dto.setCountry("India");

        assertTrue(validator.validate(dto).isEmpty());
    }

    // 12. Invalid DTO (empty fields)
    @Test
    void testOfficeDtoInvalidEmpty() {
        OfficeRequestDto dto = new OfficeRequestDto();
        dto.setCity("");

        assertFalse(validator.validate(dto).isEmpty());
    }

    // 13. Invalid DTO (phone format)
    @Test
    void testOfficeDtoInvalidPhone() {
        OfficeRequestDto dto = new OfficeRequestDto();
        dto.setOfficeCode("OF1");
        dto.setCity("Chennai");
        dto.setPhone("123"); // invalid
        dto.setAddressLine1("Street");
        dto.setCountry("India");

        assertFalse(validator.validate(dto).isEmpty());
    }
}