package ai.group2.project_management_system.controller.view;


import ai.group2.project_management_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final UserService userService;

    @GetMapping("/home")
    public String home(Model model, HttpSession session){
        var user = userService.getCurrentUser();
        System.out.println("Current User: "+ user);
        session.setAttribute("departmentId", user.getDepartment().getId());
        session.setAttribute("user", user);
        return "index";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

}
