package ai.group2.project_management_system.serviceTest;

import ai.group2.project_management_system.model.entity.Project;
import ai.group2.project_management_system.repository.ProjectRepository;
import ai.group2.project_management_system.service.Impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProject() {
        // Arrange
        Project project = new Project();
        when(projectRepository.save(project)).thenReturn(project);

        // Act
        Project savedProject = projectService.save(project);

        // Assert
        assertEquals(project, savedProject);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void testGetAllProjects() {
        // Arrange
        List<Project> projects = new ArrayList<>();
        when(projectRepository.findAll()).thenReturn(projects);

        // Act
        List<Project> result = projectService.getAllProjects();

        // Assert
        assertEquals(projects, result);
        verify(projectRepository, times(1)).findAll();
    }
}
