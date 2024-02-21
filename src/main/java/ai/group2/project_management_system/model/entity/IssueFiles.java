package ai.group2.project_management_system.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueFiles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD
    private String name;
=======
    private String fileName;
>>>>>>> 48ccadb128d1121d8e6fd2f0b6759d670c1490b0
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Issue issue;
}
