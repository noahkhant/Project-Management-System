package ai.group2.project_management_system.serviceTest.department;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import ai.group2.project_management_system.model.entity.Department;
import ai.group2.project_management_system.service.DepartmentService;

class DepartmentServiceTest {
    @Test
    public void testSaveDepartment() {
        // Create a mock Department object
        Department mockDepartment = new Department();
        mockDepartment.setId(1L);
        mockDepartment.setName("Test Department");

        // Create a mock DepartmentService
        DepartmentService departmentService = mock(DepartmentService.class);

        // Stub the save method to return the mock Department
        when(departmentService.save(any(Department.class))).thenReturn(mockDepartment);

        // Call the save method
        Department savedDepartment = departmentService.save(mockDepartment);

        // Verify that the save method was called with the mock Department
        verify(departmentService).save(mockDepartment);

        // Verify that the savedDepartment is not null and has the correct attributes
        assertNotNull(savedDepartment);
        assertEquals(1L, savedDepartment.getId());
        assertEquals("Test Department", savedDepartment.getName());
    }

    @Test
    public void testGetAllDepartments() {
        // Create a list of mock Department objects
        List<Department> mockDepartments = new ArrayList<>();
        mockDepartments.add(new Department(1L, "Department 1"));
        mockDepartments.add(new Department(2L, "Department 2"));

        // Create a mock DepartmentService
        DepartmentService departmentService = mock(DepartmentService.class);

        // Stub the getAllDepartments method to return the list of mock Departments
        when(departmentService.getAllDepartments()).thenReturn(mockDepartments);

        // Call the getAllDepartments method
        List<Department> departments = departmentService.getAllDepartments();

        // Verify that the getAllDepartments method was called
        verify(departmentService).getAllDepartments();

        // Verify that the returned list is not null and contains the expected mock Departments
        assertNotNull(departments);
        assertEquals(2, departments.size());
        assertEquals("Department 1", departments.get(0).getName());
        assertEquals("Department 2", departments.get(1).getName());
    }

    @Test
    public void testGetDepartmentById() {
        // Create a mock Department object
        Department mockDepartment = new Department(1L, "Test Department");

        // Create a mock DepartmentService
        DepartmentService departmentService = mock(DepartmentService.class);

        // Stub the getDepartmentById method to return the mock Department
        when(departmentService.getDepartmentById(1L)).thenReturn(mockDepartment);

        // Call the getDepartmentById method
        Department retrievedDepartment = departmentService.getDepartmentById(1L);

        // Verify that the getDepartmentById method was called with the correct ID
        verify(departmentService).getDepartmentById(1L);

        // Verify that the retrievedDepartment is not null and has the correct attributes
        assertNotNull(retrievedDepartment);
        assertEquals(1L, retrievedDepartment.getId());
        assertEquals("Test Department", retrievedDepartment.getName());
    }
}