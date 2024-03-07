package ai.group2.project_management_system.controller.view;

import ai.group2.project_management_system.model.entity.User;
import ai.group2.project_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class ReportController {
    private final UserService userService;
    @ModelAttribute("user")
    public User getUserFromSession() {
        User user = userService.getCurrentUser();
        return user;
    }
    @GetMapping("/report")
    public String getReport() {
        return "report";
    }
}
