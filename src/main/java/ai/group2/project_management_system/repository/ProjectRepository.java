package ai.group2.project_management_system.repository;

import ai.group2.project_management_system.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
