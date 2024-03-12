package ai.group2.project_management_system.dto;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IssueDetailsDtoTest {

    @Test
    public void testIssueDetailsDto() {
        // Create test data
        String pmName = "John Doe";
        String projectName = "Project X";
        String issueTitle = "Bug Fix";
        String priority = "High";
        String status = "Open";
        String issueType = "Bug";
        String issueCategory = "Backend";
        LocalDate startDate = LocalDate.of(2024, 3, 12);
        LocalDate dueDate = LocalDate.of(2024, 3, 20);
        LocalDate actualStartDate = LocalDate.of(2024, 3, 15);
        LocalDate actualEndDate = LocalDate.of(2024, 3, 18);
        List<String> fileNameList = Arrays.asList("file1.txt", "file2.txt");
        String subject = "Issue Subject";
        String issueDescription = "Detailed issue description";

        // Create IssueDetailsDto instance
        IssueDetailsDto issueDetailsDto = IssueDetailsDto.builder()
                .pmName(pmName)
                .projectName(projectName)
                .issueTitle(issueTitle)
                .priority(priority)
                .status(status)
                .issueType(issueType)
                .issueCategory(issueCategory)
                .startDate(startDate)
                .dueDate(dueDate)
                .actualStartDate(actualStartDate)
                .actualEndDate(actualEndDate)
                .fileNameList(fileNameList)
                .subject(subject)
                .issueDescription(issueDescription)
                .build();

        // Verify the data
        assertEquals(pmName, issueDetailsDto.getPmName());
        assertEquals(projectName, issueDetailsDto.getProjectName());
        assertEquals(issueTitle, issueDetailsDto.getIssueTitle());
        assertEquals(priority, issueDetailsDto.getPriority());
        assertEquals(status, issueDetailsDto.getStatus());
        assertEquals(issueType, issueDetailsDto.getIssueType());
        assertEquals(issueCategory, issueDetailsDto.getIssueCategory());
        assertEquals(startDate, issueDetailsDto.getStartDate());
        assertEquals(dueDate, issueDetailsDto.getDueDate());
        assertEquals(actualStartDate, issueDetailsDto.getActualStartDate());
        assertEquals(actualEndDate, issueDetailsDto.getActualEndDate());
        assertEquals(fileNameList, issueDetailsDto.getFileNameList());
        assertEquals(subject, issueDetailsDto.getSubject());
        assertEquals(issueDescription, issueDetailsDto.getIssueDescription());

        // Additional assertions
        assertTrue(issueDetailsDto.getFileNameList().containsAll(fileNameList));
    }
}
