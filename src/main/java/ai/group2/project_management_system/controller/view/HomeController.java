package ai.group2.project_management_system.controller.view;


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

    public String home( HttpSession session){


        return "index";
    }

    @GetMapping("/profile")
    public String profile(){
        /*var user = userService.getCurrentUser();*/
//        session.setAttribute("id",user.getId());
//        session.setAttribute("role",user.getRole());
        /*
        System.out.println("UserId:"+user.getId());
        System.out.println("UserName:"+user.getName());*/
       /* var user = userService.getCurrentUser();
        session.setAttribute("user", user);*/
        return "profile";
    }

}
