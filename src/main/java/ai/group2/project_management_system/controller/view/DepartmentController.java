package ai.group2.project_management_system.controller.view;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class DepartmentController {

    @GetMapping("/department")
    public String Department() {
        return "department";
    }
}
