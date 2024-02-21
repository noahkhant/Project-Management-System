package ai.group2.project_management_system.service;

import ai.group2.project_management_system.dto.UserDTO;
import ai.group2.project_management_system.model.entity.User;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
import java.util.Set;
>>>>>>> 48ccadb128d1121d8e6fd2f0b6759d670c1490b0

public interface UserService {
    User save (User user);
    List<User> getAllUsers();
    List<User> findUsersByIds(List<Long> userIds);
    List<UserDTO> getUsersByDepartmentId(Long departmentId);
    User getCurrentUser();
<<<<<<< HEAD

=======
    List<UserDTO> getUsersByProjectId(Long projectId);
>>>>>>> 48ccadb128d1121d8e6fd2f0b6759d670c1490b0
}
