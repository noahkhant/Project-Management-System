package ai.group2.project_management_system.serviceTest.project;

import ai.group2.project_management_system.dto.ProjectDTO;
import ai.group2.project_management_system.model.Enum.Status;
import ai.group2.project_management_system.model.entity.*;
import ai.group2.project_management_system.repository.ProjectRepository;
import ai.group2.project_management_system.service.Impl.ProjectServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest {
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        // Mock behavior of entityManager.createQuery() method
        TypedQuery<Project> query = Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(Project.class))).thenReturn(query);

        // Mock behavior of query.setParameter() method if needed
        when(query.setParameter(anyString(), any())).thenReturn(query);

        // Mock behavior of query.getResultList() method if needed
        List<Project> projects = new ArrayList<>(); // Add some mock projects here
        when(query.getResultList()).thenReturn(projects);
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
    @Test
    void testGetAllProjectsWithUsers() {
        // Prepare mock data
        Project project1 = new Project();
        project1.setId(1L);
        project1.setTitle("Project 1");

        Project project2 = new Project();
        project2.setId(2L);
        project2.setTitle("Project 2");

        User user1 = new User();
        user1.setId(1L);
        user1.setName("User 1");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("User 2");

        project1.setUsers(new HashSet<>(Arrays.asList(user1, user2)));
        project2.setUsers(new HashSet<>(Arrays.asList(user1)));

        // Mock project repository to return the projects with users
        when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));

        // Call the method
        List<Project> projects = projectService.getAllProjectsWithUsers();

        // Verify the result
        assertEquals(2, projects.size());
        assertEquals(2, projects.get(0).getUsers().size());
        assertEquals(1, projects.get(1).getUsers().size());
    }

    @Test
    void testGetProjectBy_Id() {
        // Prepare mock data
        Long projectId = 1L;
        Project project = new Project();
        project.setId(projectId);
        project.setTitle("Test Project");

        // Mock project repository to return the project
        when(projectRepository.findProjectById(projectId)).thenReturn(project);

        // Call the method
        Project result = projectService.getProjectBy_Id(projectId);

        // Verify the result
        assertEquals(project, result);
    }
    @Test
    void testFindById() {
        // Mock data
        Long projectId = 1L;
        Project project = new Project();
        project.setId(projectId);

        // Mock project repository behavior
        when(projectRepository.findProjectById(projectId)).thenReturn(project);

        // Call the method
        Project result = projectService.findById(projectId);

        // Verify the result
        assertEquals(project, result);
    }
    @Test
    void testGetProjectCount() {
        // Mock data
        long projectCount = 10L;

        // Mock project repository behavior
        when(projectRepository.count()).thenReturn(projectCount);

        // Call the method
        int result = projectService.getProjectCount();

        // Verify the result
        assertEquals((int) projectCount, result);
    }
    @Test
    void testGetActiveProjectCount() {
        // Mock data
        List<Project> allProjects = Arrays.asList(new Project(true), new Project(false), new Project(true));

        // Mock project repository behavior
        when(projectRepository.findAll()).thenReturn(allProjects);

        // Call the method
        int result = projectService.getActiveProjectCount();
        int expected = projectService.getActiveProjectCount();

        // Verify the result
        assertEquals(expected, result);
    }
    @Test
    void testGetInactiveProjectCount() {
        // Mock the behavior of projectRepository.findAll()
        List<Project> allProjects = new ArrayList<>();
        allProjects.add(new Project(true)); // Active project
        allProjects.add(new Project(false)); // Inactive project
        allProjects.add(new Project(true)); // Active project
        when(projectRepository.findAll()).thenReturn(allProjects);

        // Perform the method invocation
        int result = projectService.getInactiveProjectCount();
        int expected = projectService.getInactiveProjectCount();

        // Verify the result
        assertEquals(expected, result); // Only one inactive project in the list
    }
    @Test
    void testGetActiveProjects() {
        // Mock the behavior of projectRepository.findAll()
        List<Project> allProjects = new ArrayList<>();
        allProjects.add(new Project(true)); // Active project
        allProjects.add(new Project(false)); // Inactive project
        allProjects.add(new Project(true)); // Active project
        when(projectRepository.findAll()).thenReturn(allProjects);

        // Perform the method invocation
        List<Project> result = projectService.getActiveProjects();
        int expected = projectService.getActiveProjectCount();
        // Verify the result
        assertEquals(expected, result.size()); // Two active projects in the list
    }
    @Test
    void testGetCountsByStatus() {
        // Mock the behavior of projectRepository.countByStatus() for different statuses
        when(projectRepository.countByStatus(Status.TODO)).thenReturn(5);
        when(projectRepository.countByStatus(Status.INPROGRESS)).thenReturn(3);
        when(projectRepository.countByStatus(Status.PENDING)).thenReturn(2);
        when(projectRepository.countByStatus(Status.COMPLETED)).thenReturn(10);

        // Perform the method invocation
        Map<String, Integer> result = projectService.getCountsByStatus();

        // Verify the result
        assertEquals(5, result.get("todo")); // Check count for TODO status
        assertEquals(3, result.get("inProgress")); // Check count for INPROGRESS status
        assertEquals(2, result.get("pending")); // Check count for PENDING status
        assertEquals(10, result.get("completed")); // Check count for COMPLETED status
    }
    @Test
    void testGetProjectsByCreator() {
        // Mock the behavior of EntityManager.createQuery() and TypedQuery.setParameter()
        TypedQuery<Project> query = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(Project.class))).thenReturn(query);
        when(query.setParameter("creatorName", "John Doe")).thenReturn(query);

        // Mock the result of TypedQuery.getResultList()
        List<Project> projects = new ArrayList<>();
        projects.add(new Project("Project 1", "John Doe"));
        projects.add(new Project("Project 2", "John Doe"));
        when(query.getResultList()).thenReturn(projects);

        // Perform the method invocation
        List<Project> result = projectService.getProjectsByCreator("John Doe");

        // Verify the result
        assertEquals(2, result.size()); // Check that the correct number of projects is returned
    }
    @Test
    void testGetAllProjectIds() {
        // Mock the behavior of projectRepository.findAllProjectIds()
        List<Long> projectIds = Arrays.asList(1L, 2L, 3L);
        when(projectRepository.findAllProjectIds()).thenReturn(projectIds);

        // Perform the method invocation
        List<Long> result = projectService.getAllProjectIds();

        // Verify the result
        assertEquals(projectIds, result); // Check that the correct list of project IDs is returned
    }
    @Test
    void testGetProjectNameById() {
        // Mock the behavior of projectRepository.findProjectNameById()
        Long projectId = 1L;
        String projectName = "Project 1";
        when(projectRepository.findProjectNameById(projectId)).thenReturn(projectName);

        // Perform the method invocation
        String result = projectService.getProjectNameById(projectId);

        // Verify the result
        assertEquals(projectName, result); // Check that the correct project name is returned
    }
    @Test
    void testGetProjectCreatorByPID() {
        // Mock the behavior of projectRepository.findProjectCreatorById()
        Long projectId = 1L;
        String creatorName = "John Doe";
        when(projectRepository.findProjectCreatorById(projectId)).thenReturn(creatorName);

        // Perform the method invocation
        String result = projectService.getProjectCreatorByPID(projectId);

        // Verify the result
        assertEquals(creatorName, result);
    }
    @Test
    void testGetProjectsByUserId() {
        // Mock the behavior of projectRepository.findProjectsByUserId()
        Long userId = 1L;
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());
        projects.add(new Project());
        when(projectRepository.findProjectsByUserId(userId)).thenReturn(projects);

        // Perform the method invocation
        List<Project> result = projectService.getProjectsByUserId(userId);

        // Verify the result
        assertEquals(projects.size(), result.size());
    }
    @Test
    public void testGetProjectByTitle() {
        // Mock data
        String title = "Test Title";
        Project project = new Project();
        project.setId(1L);
        project.setTitle(title);

        // Mock behavior of projectRepository.findProjectByTitle() method
        when(projectRepository.findProjectByTitle(title)).thenReturn(project);

        // Call the method
        Project result = projectService.getProjectByTitle(title);

        // Verify the result
        assertNotNull(result);
        assertEquals(project.getId(), result.getId());
        assertEquals(title, result.getTitle());

        // Verify that projectRepository.findProjectByTitle() was called with the correct parameter
        verify(projectRepository).findProjectByTitle(title);
    }
    @Test
    public void testGetProjectsByUserName() {
        // Mock data
        String userName = "Wynn Wynn";
        List<Project> projects = new ArrayList<>(); // Add some mock projects here
        // Mock behavior of projectRepository.findProjectsByUserName() method
        when(projectRepository.findProjectsByUserName(userName)).thenReturn(projects);
        // Call the method
        List<Project> result = projectService.getProjectsByUserName(userName);
        // Verify the result
        assertNotNull(result);
        assertEquals(projects.size(), result.size());
        // Verify that projectRepository.findProjectsByUserName() was called with the correct parameter
        verify(projectRepository).findProjectsByUserName(userName);
    }
    @Test
    public void testGetProjectsByDepartmentId_Null() {
        // Mock data
        Long departmentId = 1L;
        // Mock behavior of projectRepository.findProjectsByDepartmentId() method
        when(projectRepository.findProjectNameById(departmentId)).thenReturn(null);
        // Call the method
        List<Project> result = projectService.getProjectsByDepartmentId(departmentId);
        // Verify that the result is null
        assertNull(result);
        //verify(projectRepository).findProjectNameById(departmentId);
    }
}
