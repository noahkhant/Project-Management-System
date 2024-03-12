package ai.group2.project_management_system.serviceTest.department;

import ai.group2.project_management_system.model.entity.Department;
import ai.group2.project_management_system.repository.DepartmentRepository;
import ai.group2.project_management_system.service.Impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        // Create a sample department
        Department department = new Department();
        department.setId(1L);
        department.setName("Department");
        department.setEmail("department@example.com");
        department.setPhone("1234567890");

        // Mock the behavior of the departmentRepository.save() method
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        // Call the save method of DepartmentService
        Department savedDepartment = departmentService.save(department);

        // Verify that departmentRepository.save() method is called once with the correct argument
        verify(departmentRepository, times(1)).save(department);

        // Verify the returned department object
        assertEquals(department, savedDepartment);
    }

    @Test
    public void testGetAllDepartments() {
        // Create sample department objects
        Department department1 = new Department();
        department1.setId(1L);
        department1.setName("Department 1");
        department1.setEmail("department1@example.com");
        department1.setPhone("1234567890");

        Department department2 = new Department();
        department2.setId(2L);
        department2.setName("Department 2");
        department2.setEmail("department2@example.com");
        department2.setPhone("9876543210");

        // Mock the behavior of the departmentRepository.findAll() method
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department1, department2));

        // Call the getAllDepartments method of DepartmentService
        List<Department> departments = departmentService.getAllDepartments();

        // Verify that departmentRepository.findAll() method is called once
        verify(departmentRepository, times(1)).findAll();

        // Verify the returned list of departments
        assertEquals(2, departments.size());
        assertEquals(department1, departments.get(0));
        assertEquals(department2, departments.get(1));
    }

    @Test
    public void testGetDepartmentById() {
        // Create a sample department object
        Department department = new Department();
        department.setId(1L);
        department.setName("Department 1");
        department.setEmail("department1@example.com");
        department.setPhone("1234567890");

        // Mock the behavior of the departmentRepository.getReferenceById() method
        when(departmentRepository.getReferenceById(1L)).thenReturn(department);

        // Call the getDepartmentById method of DepartmentService with the department ID
        Department retrievedDepartment = departmentService.getDepartmentById(1L);

        // Verify that departmentRepository.getReferenceById() method is called once with the specified department ID
        assertEquals(department, retrievedDepartment);
    }
}