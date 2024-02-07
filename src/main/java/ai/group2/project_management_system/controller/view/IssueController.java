package ai.group2.project_management_system.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IssueController {

    @GetMapping("/issue")
    public String issue(){
        return "issue";
    }

}
