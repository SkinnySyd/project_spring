package com.rabat.testProject.Service.ServiceImp;

import com.rabat.testProject.Dto.ProjectDto;
import com.rabat.testProject.Exception.ResourceNotFoundException;
import com.rabat.testProject.Model.Project;
import com.rabat.testProject.Repository.ProjectRepository;
import com.rabat.testProject.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    @Autowired
    public ProjectServiceImp(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        ;
    }
    @Override
    public void createProject(ProjectDto projectReq) {
        Project project = new Project(projectReq.getTitle(),projectReq.getDescription());
        projectRepository.save(project);
    }

    @Override
    public void updateProject(Long id, Project project) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Project not found"+project.getId()));
        existingProject.setTitle(project.getTitle());
        existingProject.setDescription(project.getDescription());
        projectRepository.save(existingProject);
    }

    @Override
    public void updateProject(Project projectReq) {
        if(!projectRepository.existsById(projectReq.getId())) {
            throw new ResourceNotFoundException("Task not found with id " + projectReq.getId());
        }
        projectRepository.save(projectReq);
    }

    @Override
    public void deleteProject(long id) {
        if(!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        projectRepository.deleteById(id);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::covertToProjectDetails)
                .collect(Collectors.toList());
    }

    private ProjectDto covertToProjectDetails(Project project) {
        ProjectDto projectDetails = new ProjectDto();
        projectDetails.setId(project.getId());
        projectDetails.setTitle(project.getTitle());
        projectDetails.setDescription(project.getDescription());
        return projectDetails;
    }

    @Override
    public ProjectDto getProjectById(long id) {
        return projectRepository.findById(id).map(this::covertToProjectDetails)
                .orElseThrow(()-> new ResourceNotFoundException("Project not found"+id));
    }
}
