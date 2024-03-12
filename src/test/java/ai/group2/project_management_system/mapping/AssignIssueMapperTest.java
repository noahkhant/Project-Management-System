package ai.group2.project_management_system.mapping;
import ai.group2.project_management_system.dto.AssignIssueDTO;
import ai.group2.project_management_system.model.Enum.Priority;
import ai.group2.project_management_system.model.Enum.Status;
import ai.group2.project_management_system.model.entity.AssignIssue;
import ai.group2.project_management_system.model.entity.Issue;
import ai.group2.project_management_system.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssignIssueMapperTest {

    @Test
    public void testMapAssignIssueToAssignIssue1() {
        // Create sample AssignIssueDTO
        AssignIssueDTO assignIssueDTO = new AssignIssueDTO();
        assignIssueDTO.setAssignName("Assignee Name");
        assignIssueDTO.setUser(new User());
        assignIssueDTO.setStatus("INPROGRESS");
        assignIssueDTO.setPriority("HIGH");
        assignIssueDTO.setPlanStartDate(LocalDate.of(2024, 3, 15));
        assignIssueDTO.setPlanDueDate(LocalDate.of(2024, 3, 30));
        assignIssueDTO.setIssue(new Issue());

        // Map AssignIssueDTO to AssignIssue using the mapper
        AssignIssue assignIssue = AssignIssueMapper.mapAssignIssueToAssignIssue1(assignIssueDTO);

        // Verify the mapping
        assertEquals(assignIssueDTO.getAssignName(), assignIssue.getName());
        assertTrue(assignIssue.isActive());
        assertEquals(assignIssueDTO.getUser(), assignIssue.getUser());
        assertEquals(Status.INPROGRESS, assignIssue.getStatus());
        assertEquals(Priority.HIGH, assignIssue.getPriority());
        assertEquals(assignIssueDTO.getPlanStartDate(), assignIssue.getPlanStartDate());
        assertEquals(assignIssueDTO.getPlanDueDate(), assignIssue.getPlanDueDate());
        assertEquals(assignIssueDTO.getIssue(), assignIssue.getIssue());
    }
}