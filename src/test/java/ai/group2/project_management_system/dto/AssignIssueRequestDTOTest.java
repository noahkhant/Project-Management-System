package ai.group2.project_management_system.dto;

import ai.group2.project_management_system.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssignIssueRequestDTOTest {
    @Test
    public void testAssignIssueRequestDTO() {
        // Create test data
        Long issueId = 1L;
        List<User> memberUsers = Arrays.asList(new User("user1"), new User("user2"));
        String projectName = "Project X";
        String issueTitle = "Bug Fix";
        String issueType = "Bug";
        String issueCategory = "Backend";
        String subject = "Issue Subject";
        String issueDescription = "Detailed issue description";
        LocalDate issuePlanStartDate = LocalDate.of(2024, 3, 12);
        LocalDate issuePlanEndDate = LocalDate.of(2024, 3, 20);
        List<String> fileNameList = Arrays.asList("file1.txt", "file2.txt");

        // Create AssignIssueRequestDTO instance
        AssignIssueRequestDTO assignIssueRequestDTO = AssignIssueRequestDTO.builder()
                .issueId(issueId)
                .memberUsers(memberUsers)
                .projectName(projectName)
                .issueTitle(issueTitle)
                .issueType(issueType)
                .issueCategory(issueCategory)
                .subject(subject)
                .issueDescription(issueDescription)
                .issuePlanStartDate(issuePlanStartDate)
                .issuePlanEndDate(issuePlanEndDate)
                .fileNameList(fileNameList)
                .build();

        // Verify the data
        assertEquals(issueId, assignIssueRequestDTO.getIssueId());
        assertEquals(memberUsers, assignIssueRequestDTO.getMemberUsers());
        assertEquals(projectName, assignIssueRequestDTO.getProjectName());
        assertEquals(issueTitle, assignIssueRequestDTO.getIssueTitle());
        assertEquals(issueType, assignIssueRequestDTO.getIssueType());
        assertEquals(issueCategory, assignIssueRequestDTO.getIssueCategory());
        assertEquals(subject, assignIssueRequestDTO.getSubject());
        assertEquals(issueDescription, assignIssueRequestDTO.getIssueDescription());
        assertEquals(issuePlanStartDate, assignIssueRequestDTO.getIssuePlanStartDate());
        assertEquals(issuePlanEndDate, assignIssueRequestDTO.getIssuePlanEndDate());
        assertEquals(fileNameList, assignIssueRequestDTO.getFileNameList());

        // Additional assertions
        assertTrue(assignIssueRequestDTO.getMemberUsers().containsAll(memberUsers));
        assertTrue(assignIssueRequestDTO.getFileNameList().containsAll(fileNameList));
    }

}