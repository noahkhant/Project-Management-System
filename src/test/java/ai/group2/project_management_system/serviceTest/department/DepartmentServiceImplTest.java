package ai.group2.project_management_system.serviceTest.department;

import ai.group2.project_management_system.model.entity.Department;
import ai.group2.project_management_system.repository.DepartmentRepository;
import ai.group2.project_management_system.service.Impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDepartment() {
        // Create a department
        Department department = new Department();
        department.setName("Test Department");

        // Mock the repository's save method to return the department
        when(departmentRepository.save(department)).thenReturn(department);

        // Save the department
        Department savedDepartment = departmentService.save(department);

        // Verify that the department is saved
        assertNotNull(1);
        assertEquals("Test Department", savedDepartment.getName());
    }

    @Test
    public void testGetAllDepartments() {
        // Create a list of departments
        List<Department> departments = new ArrayList<>();
        Department department1 = new Department();
        department1.setName("Department 1");
        Department department2 = new Department();
        department2.setName("Department 2");
        departments.add(department1);
        departments.add(department2);

        // Mock the repository's findAll method to return the list of departments
        when(departmentRepository.findAll()).thenReturn(departments);

        // Get all departments
        List<Department> returnedDepartments = departmentService.getAllDepartments();

        // Verify that the returned list matches the expected list
        assertEquals(departments.size(), returnedDepartments.size());
        assertEquals(departments.get(0).getName(), returnedDepartments.get(0).getName());
        assertEquals(departments.get(1).getName(), returnedDepartments.get(1).getName());
    }

    @Test
    public void testGetDepartmentById() {
        // Create a department
        Long departmentId = 1L;
        Department department = new Department();
        department.setId(departmentId);
        department.setName("Test Department");

        // Mock the repository's getReferenceById method to return the department
        when(departmentRepository.getReferenceById(departmentId)).thenReturn(department);

        // Get department by ID
        Department returnedDepartment = departmentService.getDepartmentById(departmentId);

        // Verify that the returned department matches the expected department
        assertNotNull(returnedDepartment);
        assertEquals(departmentId, returnedDepartment.getId());
        assertEquals("Test Department", returnedDepartment.getName());
    }
}