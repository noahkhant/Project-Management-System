package ai.group2.project_management_system.dto;
import ai.group2.project_management_system.model.Enum.Category;
import ai.group2.project_management_system.model.Enum.Priority;
import ai.group2.project_management_system.model.Enum.Status;
import ai.group2.project_management_system.model.entity.Project;
import ai.group2.project_management_system.model.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProjectDTOTest {

    @Test
    public void testProjectDTO() {
        // Create test data
        Long id = 1L;
        String title = "Project X";
        String objective = "Objective of Project X";
        String description = "Description of Project X";
        String creator = "John Doe";
        LocalDate planStartDate = LocalDate.of(2024, 3, 1);
        LocalDate planEndDate = LocalDate.of(2024, 3, 31);
        Status status = Status.INPROGRESS;
        Category category = Category.HEALTHCARE;
        Priority priority = Priority.HIGH;
        boolean isActive = true;
        LocalDate actualStartDate = LocalDate.of(2024, 3, 1);
        LocalDate actualEndDate = LocalDate.of(2024, 3, 15);
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        Set<User> users = new HashSet<>();
        users.add(new User("user1"));
        users.add(new User("user2"));

        // Create Project entity
        Project project = new Project();
        project.setId(id);
        project.setTitle(title);
        project.setObjective(objective);
        project.setDescription(description);
        project.setCreator(creator);
        project.setPlanStartDate(planStartDate);
        project.setPlanEndDate(planEndDate);
        project.setStatus(status);
        project.setCategory(category);
        project.setPriority(priority);
        project.setIsActive(isActive);
        project.setActualStartDate(actualStartDate);
        project.setActualEndDate(actualEndDate);
        project.setUserIds(userIds);
        project.setUsers(users);

        // Create ProjectDTO instance
        ProjectDTO projectDTO = new ProjectDTO(project);

        // Verify the data
        assertEquals(id, projectDTO.getId());
        assertEquals(title, projectDTO.getTitle());
        assertEquals(objective, projectDTO.getObjective());
        assertEquals(description, projectDTO.getDescription());
        assertEquals(creator, projectDTO.getCreator());
        assertEquals(planStartDate, projectDTO.getPlanStartDate());
        assertEquals(planEndDate, projectDTO.getPlanEndDate());
        assertEquals(status, projectDTO.getStatus());
        assertEquals(category, projectDTO.getCategory());
        assertEquals(priority, projectDTO.getPriority());
        assertEquals(isActive, projectDTO.isActive());
        assertEquals(actualStartDate, projectDTO.getActualStartDate());
        assertEquals(actualEndDate, projectDTO.getActualEndDate());
        assertTrue(projectDTO.getUserIds().containsAll(userIds));
        assertTrue(projectDTO.getUsers().containsAll(users));
    }
}
