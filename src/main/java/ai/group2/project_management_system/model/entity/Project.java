package ai.group2.project_management_system.model.entity;

import ai.group2.project_management_system.model.Enum.Category;
import ai.group2.project_management_system.model.Enum.Priority;
import ai.group2.project_management_system.model.Enum.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "users")
//@EqualsAndHashCode(exclude = "users")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String objective;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String creator;

    private LocalDate planStartDate;

    private LocalDate planEndDate;
    private boolean is_active;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate actualStartDate;

    private LocalDate actualEndDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "project_member",  // Specify the name of the join table
            joinColumns = @JoinColumn(name = "project_id"),  // Column in the join table for Project
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false)  // Column in the join table for Architecture
    )
    private Set<User> users;

}
