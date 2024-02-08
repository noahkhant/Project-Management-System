package ai.group2.project_management_system.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IssueController {

    @GetMapping("/issueboard")
    public String issue(){
        return "issueboard";
    }

    @GetMapping("/issue-list")
    public String issueList(){
        return "issuelist";
    }

    @GetMapping("/issue-details")
    public String issueDetails(){
        return "issue-details";
    }
}
