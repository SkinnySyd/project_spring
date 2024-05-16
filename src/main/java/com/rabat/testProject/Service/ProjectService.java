package com.rabat.testProject.Service;

import com.rabat.testProject.Dto.ProjectDto;
import com.rabat.testProject.Model.Project;

import java.util.List;

public interface ProjectService {
    void createProject(ProjectDto project);

    void updateProject(Long id, Project project);
    void updateProject(Project project);

    void deleteProject(long id);

    List<ProjectDto> getAllProjects();
    ProjectDto getProjectById(long id);
}
