package ai.group2.project_management_system.dto;

import ai.group2.project_management_system.model.entity.Issue;
import ai.group2.project_management_system.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssignIssueDTOTest {
    @Test
    public void testAssignIssueDTO() {
        // Create test data
        Long id = 1L;
        Issue issue = new Issue();
        String assignName = "John Doe";
        boolean isActive = true;
        User user = new User();
        String status = "Open";
        String priority = "High";
        LocalDate planStartDate = LocalDate.of(2024, 3, 12);
        LocalDate planDueDate = LocalDate.of(2024, 3, 20);
        LocalDate actualStartDate = LocalDate.of(2024, 3, 15);
        LocalDate actualDueDate = LocalDate.of(2024, 3, 25);

        // Create AssignIssueDTO instance
        AssignIssueDTO assignIssueDTO = AssignIssueDTO.builder()
                .id(id)
                .issue(issue)
                .assignName(assignName)
                .isActive(isActive)
                .user(user)
                .status(status)
                .priority(priority)
                .planStartDate(planStartDate)
                .planDueDate(planDueDate)
                .actualStartDate(actualStartDate)
                .actualDueDate(actualDueDate)
                .build();

        // Verify the data
        assertEquals(id, assignIssueDTO.getId());
        assertEquals(issue, assignIssueDTO.getIssue());
        assertEquals(assignName, assignIssueDTO.getAssignName());
        assertEquals(isActive, assignIssueDTO.isActive());
        assertEquals(user, assignIssueDTO.getUser());
        assertEquals(status, assignIssueDTO.getStatus());
        assertEquals(priority, assignIssueDTO.getPriority());
        assertEquals(planStartDate, assignIssueDTO.getPlanStartDate());
        assertEquals(planDueDate, assignIssueDTO.getPlanDueDate());
        assertEquals(actualStartDate, assignIssueDTO.getActualStartDate());
        assertEquals(actualDueDate, assignIssueDTO.getActualDueDate());
    }

}