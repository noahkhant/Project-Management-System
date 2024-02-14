package ai.group2.project_management_system.dto;

import ai.group2.project_management_system.model.Enum.Category;
import ai.group2.project_management_system.model.Enum.Priority;
import ai.group2.project_management_system.model.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long id;

    private String title;

    private String objective;
    private String description;

    private String creator;

    private LocalDate planStartDate;

    private LocalDate planEndDate;
    private Status status;
    private Category category;

    private Priority priority;

    private Boolean isActive;

    private LocalDate actualStartDate;

    private LocalDate actualEndDate;
    private List<Long> userIds;
}
