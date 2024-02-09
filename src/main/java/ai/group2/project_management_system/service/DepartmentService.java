package ai.group2.project_management_system.service;

import ai.group2.project_management_system.model.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department save(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(Integer id);

}
