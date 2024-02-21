package ai.group2.project_management_system.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Method;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
=======

>>>>>>> 48ccadb128d1121d8e6fd2f0b6759d670c1490b0
    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean is_active;

}
