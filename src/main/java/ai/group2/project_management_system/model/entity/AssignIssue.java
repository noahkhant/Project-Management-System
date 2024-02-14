package ai.group2.project_management_system.model.entity;

import ai.group2.project_management_system.model.Enum.Priority;
import ai.group2.project_management_system.model.Enum.Status;
import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "assign_issue")
public class AssignIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    private boolean is_active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "plan_start_date")
    private Date planStartDate;

    @Column(name = "plan_due_date")
    private Date planDueDate;

    @Column(name = "actual_start_date")
    private Date actualStartDate;

    @Column(name = "actual_due_date")
    private Date actualDueDate;
}
