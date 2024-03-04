package ai.group2.project_management_system.controller.api;

import ai.group2.project_management_system.model.entity.Project;
import ai.group2.project_management_system.model.entity.User;
import ai.group2.project_management_system.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardDataAPI {
    private final ProjectService projectService;

    public DashboardDataAPI(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects/count/active")
    public int getActiveProjectCount(){
        return projectService.getActiveProjectCount();
    }

    @GetMapping("/project/count/all")
    public int getProjectCount(){
        return projectService.getProjectCount();
    }

    @GetMapping("/projects/count/inactive")
    public int getInactiveProjectCount(){
        return projectService.getInactiveProjectCount();
    }

    @GetMapping("/projects-for-current-user")
    public List<Project> activeProjectTable( HttpServletRequest session){
        Long departmentId = (Long) session.getAttribute("departmentId");
        System.out.println(departmentId);
        return projectService.getProjectsByDepartmentId(departmentId);

    }



}
