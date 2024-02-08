package ai.group2.project_management_system.service.Impl;

import ai.group2.project_management_system.model.entity.Project;
import ai.group2.project_management_system.repository.ProjectRepository;
import ai.group2.project_management_system.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }
}
