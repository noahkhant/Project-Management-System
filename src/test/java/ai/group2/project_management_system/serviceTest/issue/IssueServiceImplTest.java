package ai.group2.project_management_system.serviceTest.issue;

import ai.group2.project_management_system.dto.IssueDetailsDto;
import ai.group2.project_management_system.model.Enum.Status;
import ai.group2.project_management_system.model.entity.Issue;
import ai.group2.project_management_system.model.entity.IssueFiles;
import ai.group2.project_management_system.repository.IssueFileRepository;
import ai.group2.project_management_system.repository.IssueRepository;
import ai.group2.project_management_system.service.Impl.IssueServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IssueServiceImplTest {
    @Mock
    private IssueRepository issueRepository;

    @Mock
    private IssueFileRepository issueFileRepository;

    @InjectMocks
    private IssueServiceImpl issueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        // Mock data
        Issue issue = new Issue(); // Replace with your actual Issue object

        // Mock the behavior of issueRepository.save() method
        when(issueRepository.save(issue)).thenReturn(issue);

        // Call the method
        Issue result = issueService.save(issue);

        // Verify the result
        assertEquals(issue, result);

        // Verify that issueRepository.save() was called with the correct argument
        verify(issueRepository).save(issue);
    }

    @Test
    public void testGetAllIssues() {
        // Mock data
        List<Issue> expectedIssues = new ArrayList<>(); // Replace with your expected list of Issue objects

        // Mock the behavior of issueRepository.findAll() method
        when(issueRepository.findAll()).thenReturn(expectedIssues);

        // Call the method
        List<Issue> result = issueService.getAllIssues();

        // Verify the result
        assertEquals(expectedIssues, result);

        // Verify that issueRepository.findAll() was called
        verify(issueRepository).findAll();
    }

    @Test
    public void testGetIssuesByTeamleaderId() {
        // Mock data
        Long teamLeaderId = 123L; // Replace with your actual team leader ID
        List<Issue> expectedIssues = new ArrayList<>(); // Replace with your expected list of Issue objects

        // Mock the behavior of issueRepository.getIssuesByTeamLeaderId() method
        when(issueRepository.getIssuesByTeamLeaderId(teamLeaderId)).thenReturn(expectedIssues);

        // Call the method
        List<Issue> result = issueService.getIssuesByTeamleaderId(teamLeaderId);

        // Verify the result
        assertEquals(expectedIssues, result);

        // Verify that issueRepository.getIssuesByTeamLeaderId() was called with the correct argument
        verify(issueRepository).getIssuesByTeamLeaderId(teamLeaderId);
    }

    @Test
    public void testGetAllIssueByUserId() {
        // Mock data
        Long userId = 1L; // Replace with your actual user ID
        List<Issue> expectedIssues = new ArrayList<>(); // Replace with your expected list of Issue objects

        // Mock the behavior of issueRepository.findAllIssueByUserId() method
        when(issueRepository.findAllIssueByUserId(userId)).thenReturn(expectedIssues);

        // Call the method
        List<Issue> result = issueService.getAllIssueByUserId(userId);
    }
    @Test
    public void testFindIssuesByProjectId() {
        // Mock data
        Long projectId = 1L; // Replace with your actual project ID
        List<Issue> expectedIssues = new ArrayList<>(); // Replace with your expected list of Issue objects

        // Mock the behavior of issueRepository.findIssuesByProjectId() method
        when(issueRepository.findIssuesByProjectId(projectId)).thenReturn(expectedIssues);

        // Call the method
        List<Issue> result = issueService.findIssuesByProjectId(projectId);

        // Verify the result
        assertEquals(expectedIssues, result);

        // Verify that issueRepository.findIssuesByProjectId() was called with the correct argument
        verify(issueRepository).findIssuesByProjectId(projectId);
    }
    @Test
    public void testGetIssueCount() {
        // Mock data
        long expectedCount = 10; // Replace with your expected count of issues

        // Mock the behavior of issueRepository.count() method
        when(issueRepository.count()).thenReturn(expectedCount);

        // Call the method
        int result = issueService.getIssueCount();

        // Verify the result
        assertEquals(expectedCount, result);

        // Verify that issueRepository.count() was called
        verify(issueRepository).count();
    }
    @Test
    public void testGetActiveIssueCount() {
        // Mock data
        List<Issue> allIssues = new ArrayList<>(); // Replace with your list of all issues
        // Add some Issue objects to the list

        // Mock the behavior of issueRepository.findAll() method
        when(issueRepository.findAll()).thenReturn(allIssues);

        // Call the method
        int result = issueService.getActiveIssueCount();

        // Verify the result
        // Add assertions to verify that the result matches the expected count of active issues

        // Verify that issueRepository.findAll() was called
        verify(issueRepository).findAll();
    }
    @Test
    public void testGetInactiveIssueCount() {
        // Mock data
        List<Issue> allIssues = new ArrayList<>(); // Replace with your list of all issues
        // Add some Issue objects to the list

        // Mock the behavior of issueRepository.findAll() method
        when(issueRepository.findAll()).thenReturn(allIssues);

        // Call the method
        int result = issueService.getInactiveIssueCount();
        verify(issueRepository).findAll();
    }

    @Test
    public void testGetIssueById() {
        // Mock data
        Long issueId = 1L; // Replace with your actual issue ID
        Issue expectedIssue = new Issue(); // Replace with your expected Issue object

        // Mock the behavior of issueRepository.getIssueById() method
        when(issueRepository.getIssueById(issueId)).thenReturn(expectedIssue);

        // Call the method
        Issue result = issueService.getIssueById(issueId);

        // Verify the result
        assertEquals(expectedIssue, result);

        // Verify that issueRepository.getIssueById() was called with the correct argument
        verify(issueRepository).getIssueById(issueId);
    }

    @Test
    public void testGetCountsByStatus() {
        // Mock data
        Map<Status, Long> countByStatusMap = new HashMap<>(); // Replace with your map of count by status
        // Add counts for different statuses to the map

        // Mock the behavior of issueRepository.countByStatus() method for each status
        for (Map.Entry<Status, Long> entry : countByStatusMap.entrySet()) {
            when(issueRepository.countByStatus(entry.getKey())).thenReturn(Math.toIntExact(entry.getValue()));
        }

        // Call the method
        Map<String, Integer> result = issueService.getCountsByStatus();

        // Verify that issueRepository.countByStatus() was called with the correct status for each status
        for (Map.Entry<Status, Long> entry : countByStatusMap.entrySet()) {
            verify(issueRepository).countByStatus(entry.getKey());
        }
    }


}