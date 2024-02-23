package ai.group2.project_management_system.controller.view;

import ai.group2.project_management_system.dto.AssignIssueDTO;
import ai.group2.project_management_system.dto.IssueDetailsDto;
import ai.group2.project_management_system.mapping.AssignIssueMapper;
import ai.group2.project_management_system.model.Enum.Priority;
import ai.group2.project_management_system.model.Enum.Status;
import ai.group2.project_management_system.model.entity.AssignIssue;
import ai.group2.project_management_system.model.entity.Department;
import ai.group2.project_management_system.model.entity.Issue;
import ai.group2.project_management_system.repository.AssignIssueRepository;
import ai.group2.project_management_system.repository.IssueRepository;
import ai.group2.project_management_system.service.DepartmentService;
import ai.group2.project_management_system.service.IssueService;
import ai.group2.project_management_system.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TeamLeaderController {
    private final IssueService issueService;
    private final UserService userService;
    private final IssueRepository issueRepository;
    private final AssignIssueRepository assignIssueRepository;
    private final AssignIssueMapper assignIssueMapper;
    @GetMapping("/teamleader-issueboard")
    public String DepartmentHeaderIssueBoard(HttpSession httpSession,Model model) {
        Long teamleaderId= (Long) httpSession.getAttribute("id");
        List<Issue> teamLeaderIssues=issueService.getIssuesByTeamleaderId(teamleaderId);
        List<Issue> currentTeamLeaderIssues=new ArrayList<Issue>();
        for (int i = 0; i < teamLeaderIssues.size(); i++) {
            if (!teamLeaderIssues.get(i).getProject().getStatus().equals(Status.COMPLETED)) {
                currentTeamLeaderIssues.add(teamLeaderIssues.get(i));
            }
        }
     //   log.info("Issues list -> {}",currentTeamLeaderIssues.size());

        model.addAttribute("issues",currentTeamLeaderIssues);
        return "teamleader-issueboard";
    }

//    @GetMapping("/chatlist")
//    public String Chatlist() {
//        return "chatlist";
//    }

    @GetMapping("/teamleader-issuelist")
    public String DepartmentHeaderIssueList(Model model, HttpSession httpSession) {
        Long teamleaderId = (Long) httpSession.getAttribute("id");
        if (teamleaderId != null) {
            List<Issue> issues = issueService.getIssuesByTeamleaderId(teamleaderId);
            model.addAttribute("issues", issues);
            model.addAttribute("assignIssue", new AssignIssueDTO());
            return "teamleader-issuelist";
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping("/assignIssue")
    public String assignIssue(@ModelAttribute("assignIssue") AssignIssueDTO assignIssueDTO){
        AssignIssue assignIssue = AssignIssueMapper.mapAssignIssueToAssignIssue1(assignIssueDTO);
        assignIssueRepository.save(assignIssue);
        return "redirect:/teamleader-issueboard";
    }

    @GetMapping("/member-issuelist")
    public String MemberIssueList() {
        return "members-view";
    }

    @GetMapping("/teamleader-issuedetails/{id}")
    public String ViewIssueDetails(@PathVariable Long id,Model model) {
        IssueDetailsDto issue=issueService.getIssueDetailsById(id);
        model.addAttribute("issue",issue);
      //  log.info("Issue -> {}",issue);
        return "teamleader-issuedetails";
    }
}
