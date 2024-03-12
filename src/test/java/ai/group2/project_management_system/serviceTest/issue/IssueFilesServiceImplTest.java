package ai.group2.project_management_system.serviceTest.issue;

import ai.group2.project_management_system.model.entity.IssueFiles;
import ai.group2.project_management_system.repository.IssueFilesRepository;
import ai.group2.project_management_system.service.Impl.IssueFilesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IssueFilesServiceImplTest {

    @Mock
    private IssueFilesRepository issueFilesRepository;

    @InjectMocks
    private IssueFilesServiceImpl issueFilesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testSave() {
        // Mock data
        IssueFiles files = new IssueFiles(); // Replace with your actual IssueFiles object

        // Mock the behavior of issueFilesRepository.save() method
        when(issueFilesRepository.save(files)).thenReturn(files);

        // Call the method
        IssueFiles result = issueFilesService.save(files);

        // Verify the result
        assertEquals(files, result);

        // Verify that issueFilesRepository.save() was called with the correct argument
        verify(issueFilesRepository).save(files);
    }
    @Test
    public void testFindByIssueIds() {
        // Mock data
        Long issueId = 123L; // Replace with your actual issue ID
        List<IssueFiles> expectedFiles = new ArrayList<>(); // Replace with your expected list of IssueFiles objects

        // Mock the behavior of issueFilesRepository.findAllByIssueId() method
        when(issueFilesRepository.findAllByIssueId(issueId)).thenReturn(expectedFiles);

        // Call the method
        List<IssueFiles> result = issueFilesService.findByIssueIds(issueId);

        // Verify the result
        assertEquals(expectedFiles, result);

        // Verify that issueFilesRepository.findAllByIssueId() was called with the correct argument
        verify(issueFilesRepository).findAllByIssueId(issueId);
    }
}