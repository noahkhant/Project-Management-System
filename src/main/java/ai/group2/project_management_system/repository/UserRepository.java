package ai.group2.project_management_system.repository;

import ai.group2.project_management_system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


}
