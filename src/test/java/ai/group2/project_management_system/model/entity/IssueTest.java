package ai.group2.project_management_system.model.entity;

import ai.group2.project_management_system.model.Enum.*;
import ai.group2.project_management_system.model.entity.Issue;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IssueTest {

    @Mock
    private Issue issue;

    @Test
    public void testIssueProperties() {
        Issue issue = new Issue();
        issue.setId(1L);
        issue.setTitle("Test Issue");
        issue.setIssueType("Bug");
        issue.setPercentage("100%");
        issue.setPriority(Priority.HIGH);
        issue.setSubject("Test Subject");
        issue.setDescription("Test Description");
        issue.setCreator("Test Creator");
        issue.setActive(true);
        issue.setAssigned(false);
        issue.setStatus(Status.INPROGRESS);
        issue.setPlanStartDate(LocalDate.now());
        issue.setPlanDueDate(LocalDate.now().plusDays(7));
        issue.setActualStartDate(null);
        issue.setActualDueDate(null);
        issue.setTeamLeaderId(1L);

        assertEquals(1L, issue.getId());
        assertEquals("Test Issue", issue.getTitle());
        assertEquals("Bug", issue.getIssueType());
        assertEquals("100%", issue.getPercentage());
        assertEquals(Priority.HIGH, issue.getPriority());
        assertEquals("Test Subject", issue.getSubject());
        assertEquals("Test Description", issue.getDescription());
        assertEquals("Test Creator", issue.getCreator());
        assertEquals(true, issue.isActive());
        assertEquals(false, issue.isAssigned());
        assertEquals(Status.INPROGRESS, issue.getStatus());
        assertEquals(LocalDate.now(), issue.getPlanStartDate());
        assertEquals(LocalDate.now().plusDays(7), issue.getPlanDueDate());
        assertEquals(null, issue.getActualStartDate());
        assertEquals(null, issue.getActualDueDate());
        assertEquals(1L, issue.getTeamLeaderId());
    }
}
