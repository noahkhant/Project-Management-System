package ai.group2.project_management_system.serviceTest.department;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ai.group2.project_management_system.controller.api.DepartmentAPI;
import ai.group2.project_management_system.model.entity.Department;
import ai.group2.project_management_system.service.DepartmentService;
import ai.group2.project_management_system.service.Impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

class DepartmentAPITest {

    @Mock
    private DepartmentServiceImpl departmentService;
    @InjectMocks
    private DepartmentAPI departmentAPI;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDepartments() {
        // Arrange
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(1L, "Department 1", "email1@example.com", "123456", true));
        departments.add(new Department(2L, "Department 2", "email2@example.com", "789012", true));
        when(departmentService.getAllDepartments()).thenReturn(departments);

        // Act
        ResponseEntity<List<Department>> response = departmentAPI.getDepartments();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departments, response.getBody());
    }

    @Test
    public void testGetDepartmentsWhenEmpty() {
        // Arrange
        List<Department> departments = new ArrayList<>();
        when(departmentService.getAllDepartments()).thenReturn(departments);

        // Act
        ResponseEntity<List<Department>> response = departmentAPI.getDepartments();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }
    @Test
    public void testAddDepartment() {
        // Create a sample department to be added
        Department departmentToAdd = new Department();
        departmentToAdd.setName("Sample Department");
        departmentToAdd.setEmail("sample@example.com");
        departmentToAdd.setPhone("1234567890");

        // Create a sample department that will be returned after adding
        Department savedDepartment = new Department();
        savedDepartment.setId(1L);
        savedDepartment.setName("Sample Department");
        savedDepartment.setEmail("sample@example.com");
        savedDepartment.setPhone("1234567890");
        savedDepartment.setActive(true);

        // Mock the behavior of the departmentService.save() method
        when(departmentService.save(any(Department.class))).thenReturn(savedDepartment);

        // Call the addDepartment method of DepartmentAPI
        ResponseEntity<Department> responseEntity = departmentAPI.addDepartment(departmentToAdd);

        // Verify that departmentService.save() method is called once with the correct argument
        verify(departmentService, times(1)).save(departmentToAdd);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(savedDepartment, responseEntity.getBody());
    }
    @Test
    public void testUpdateDepartment() {
        // Create a sample department ID
        Long departmentId = 1L;

        // Create a sample existing department
        Department existingDepartment = new Department();
        existingDepartment.setId(departmentId);
        existingDepartment.setName("Existing Department");
        existingDepartment.setEmail("existing@example.com");
        existingDepartment.setPhone("1234567890");

        // Create a sample updated department
        Department updatedDepartment = new Department();
        updatedDepartment.setId(departmentId);  // Same ID as the existing department
        updatedDepartment.setName("Updated Department");
        updatedDepartment.setEmail("updated@example.com");
        updatedDepartment.setPhone("9876543210");

        // Mock the behavior of the departmentService.getDepartmentById() method
        when(departmentService.getDepartmentById(departmentId)).thenReturn(existingDepartment);

        // Mock the behavior of the departmentService.save() method
        when(departmentService.save(any(Department.class))).thenReturn(updatedDepartment);

        // Call the updateDepartment method of DepartmentAPI
        ResponseEntity<Department> responseEntity = departmentAPI.updateDepartment(departmentId, updatedDepartment);

        // Verify that departmentService.getDepartmentById() is called once with the correct ID
        verify(departmentService, times(1)).getDepartmentById(departmentId);

        // Verify that departmentService.save() is called once with the correct department
        verify(departmentService, times(1)).save(existingDepartment);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedDepartment, responseEntity.getBody());
    }

    @Test
    public void testUpdateDepartmentNotFound() {
        // Create a sample department ID
        Long departmentId = 1L;

        // Mock the behavior of the departmentService.getDepartmentById() method to return null (department not found)
        when(departmentService.getDepartmentById(departmentId)).thenReturn(null);

        // Call the updateDepartment method of DepartmentAPI
        ResponseEntity<Department> responseEntity = departmentAPI.updateDepartment(departmentId, new Department());

        // Verify that departmentService.getDepartmentById() is called once with the correct ID
        verify(departmentService, times(1)).getDepartmentById(departmentId);

        // Verify the response entity
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testDepartmentStatus_Active() {
        // Create a sample department
        Department department = new Department();
        department.setId(1L);
        department.setName("Department");
        department.setEmail("department@example.com");
        department.setPhone("1234567890");
        department.setActive(true);

        // Mock the behavior of the departmentService.getDepartmentById() method
        when(departmentService.getDepartmentById(1L)).thenReturn(department);

        // Mock the behavior of the departmentService.save() method
        when(departmentService.save(department)).thenReturn(department);

        // Call the departmentStatus method of DepartmentAPI
        ResponseEntity<String> responseEntity = departmentAPI.departmentStatus(1L);

        // Verify that departmentService.getDepartmentById() method is called once with the correct argument
        verify(departmentService, times(1)).getDepartmentById(1L);

        // Verify that departmentService.save() method is called once with the correct argument
        verify(departmentService, times(1)).save(department);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Department status false changed successfully", responseEntity.getBody());
    }

    @Test
    public void testDepartmentStatus_Inactive() {
        // Create a sample department
        Department department = new Department();
        department.setId(1L);
        department.setName("Department");
        department.setEmail("department@example.com");
        department.setPhone("1234567890");
        department.setActive(false);

        // Mock the behavior of the departmentService.getDepartmentById() method
        when(departmentService.getDepartmentById(1L)).thenReturn(department);

        // Mock the behavior of the departmentService.save() method
        when(departmentService.save(department)).thenReturn(department);

        // Call the departmentStatus method of DepartmentAPI
        ResponseEntity<String> responseEntity = departmentAPI.departmentStatus(1L);

        // Verify that departmentService.getDepartmentById() method is called once with the correct argument
        verify(departmentService, times(1)).getDepartmentById(1L);

        // Verify that departmentService.save() method is called once with the correct argument
        verify(departmentService, times(1)).save(department);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Department status true changed successfully", responseEntity.getBody());
    }

}