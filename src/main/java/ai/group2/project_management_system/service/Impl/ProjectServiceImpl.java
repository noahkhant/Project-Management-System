package ai.group2.project_management_system.service.Impl;

import ai.group2.project_management_system.model.entity.Project;
import ai.group2.project_management_system.model.entity.User;
import ai.group2.project_management_system.repository.ProjectRepository;
import ai.group2.project_management_system.repository.*;
import ai.group2.project_management_system.service.ProjectService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final EntityManager entityManager;

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long projectId) {
        return projectRepository.findProjectById(projectId);
    }



}
