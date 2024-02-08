package ai.group2.project_management_system.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TeamLeaderController {
    @GetMapping("/teamleader-issueboard")
    public String DepartmentHeaderIssueBoard() {
        return "teamleader-issueboard";
    }

    @GetMapping("/chatlist")
    public String Chatlist() {
        return "chatlist";
    }

    @GetMapping("/teamleader-issuelist")
    public String DepartmentHeaderIssueList() {
        return "teamleader-issuelist";
    }

    @GetMapping("/member-issuelist")
    public String MemberIssueList() {
        return "members-view";
    }

    @GetMapping("/teamleader-issuedetails")
    public String ViewIssueDetails() {
        return "teamleader-issuedetails";
    }
}
