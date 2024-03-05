package ai.group2.project_management_system.controller.api;

import ai.group2.project_management_system.model.entity.Project;
import ai.group2.project_management_system.model.entity.User;
import ai.group2.project_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileAPI {
    private final UserService userService;

    @GetMapping("/user/profile")
    public ResponseEntity<User> getCurrentUser() {
       User currentUser = userService.getCurrentUser();
       System.out.println(currentUser);
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity<User> editUser(@PathVariable("id") Long userId, @RequestBody User user){
        System.out.println("we reach edit mapping!");

        User user1 = userService.getUserById(userId);
        if (user1 != null) {

            user1.setName(user.getName());
            user1.setAddress(user.getAddress());
            user1.setDob(user.getDob());
            user1.setEducation(user.getEducation());
            user1.setEmail(user.getEmail());
            user1.setGender(user.getGender());
            user1.setPhone(user.getPhone());
            user1.setDepartment(user.getDepartment());
            user1.setPosition(user.getPosition());
            user1.setProfilePictureFileName(user.getProfilePictureFileName());
            User latestUser = userService.save(user1);
            return ResponseEntity.ok(latestUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
