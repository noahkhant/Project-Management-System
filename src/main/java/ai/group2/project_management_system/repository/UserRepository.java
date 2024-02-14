package ai.group2.project_management_system.repository;

import ai.group2.project_management_system.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
<<<<<<< HEAD

    List<User> findByIdIn(List<Long> userIds);

    List<User> findByDepartmentId(Long departmentId);
=======
    Optional<User> findByEmail(String email);
>>>>>>> ba01fb1f05e63036b65785dbb89732e82390d959
}
