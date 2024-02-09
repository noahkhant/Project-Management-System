package ai.group2.project_management_system.controller.view;

import ai.group2.project_management_system.model.entity.Department;
import ai.group2.project_management_system.model.entity.Issue;
import ai.group2.project_management_system.service.DepartmentService;
import ai.group2.project_management_system.service.IssueService;
import ai.group2.project_management_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeamLeaderController {
    private final IssueService issueService;
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
        List<Issue> issues = issueService.getAllIssues();

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
