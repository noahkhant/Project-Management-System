package ai.group2.project_management_system.controller.view;


import ai.group2.project_management_system.model.Enum.Role;
import ai.group2.project_management_system.model.entity.Department;
import ai.group2.project_management_system.model.entity.User;
import ai.group2.project_management_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final UserService userService;

    @ModelAttribute("user")
    public User getUserFromSession(HttpSession session) {
        User user = userService.getCurrentUser();
        return user;
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        var user = userService.getCurrentUser();

        System.out.println("Current User: " + user);

        if (user.getRole() != Role.PM || user.getRole() != Role.PMO) {
            Department department = user.getDepartment();
            if (department != null) {
                session.setAttribute("departmentId", department.getId());
            } else {
                System.out.println("There is no department!");
            }
        }

        session.setAttribute("user", user);
        return "index";
    }


    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

}
