package ai.group2.project_management_system.model.entity;

import ai.group2.project_management_system.model.Enum.Priority;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Entity
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(nullable = false)
    private String subject;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String creator;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private LocalDate planStartDate;

    @Column(nullable = false)
    private LocalDate planDueDate;

    @Column(nullable = true)
    private LocalDate actualStartDate;

    @Column(nullable = true)
    private LocalDate actualDueDate;

    @Column(nullable = true)
    private String filename;

    @Transient
    private MultipartFile file;




}
