package ai.group2.project_management_system.service;

import ai.group2.project_management_system.model.entity.User;

import java.util.List;

public interface UserService {
    User save (User user);
    List<User> getAllUsers();
}
