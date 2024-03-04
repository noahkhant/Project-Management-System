package ai.group2.project_management_system.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne(cascade = CascadeType.MERGE)
    private User sender;
    @ManyToOne(cascade = CascadeType.MERGE)
    private User sendTo;

    private  String redirectURL;
}
