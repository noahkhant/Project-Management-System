package ai.group2.project_management_system.service;

import ai.group2.project_management_system.dto.ProjectDTO;
import ai.group2.project_management_system.model.entity.Project;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProjectService {
    Project save(Project project);

    List<Project> getAllProjectsWithUsers();
    Project getProjectBy_Id(Long projectId);
    List<Project> getAllProjects();
    ProjectDTO getProjectById(Long id);
    List<Long> getUserIdsByProjectId(Long projectId);
    Project findById(Long projectId);
    int getProjectCount();

    int getActiveProjectCount();

    int getInactiveProjectCount();

    List<Project> getActiveProjects();
    List<Project> getProjectsByDepartmentId(Long departmentId);

    Map<String, Integer> getCountsByStatus();
}
