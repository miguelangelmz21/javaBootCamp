package com.example.demo.services;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entities.Project;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return convertToDTO(project);
    }

    @Transactional(readOnly = true)
    public List<ProjectDTO> getProjectsByUserId(Long userId) {
        return projectRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProjectDTO> getActiveProjectsByUserId(Long userId) {
        return projectRepository.findByUserIdAndActive(userId, true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        UserEntity user = userRepository.findById(projectDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + projectDTO.getUserId()));

        Project project = convertToEntity(projectDTO);
        project.setUser(user);

        Project savedProject = projectRepository.save(project);
        return convertToDTO(savedProject);
    }

    @Transactional
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        existingProject.setName(projectDTO.getName());
        existingProject.setDescription(projectDTO.getDescription());

        if (projectDTO.getActive() != null) {
            existingProject.setActive(projectDTO.getActive());
        }

        if (projectDTO.getUserId() != null && !existingProject.getUser().getId().equals(projectDTO.getUserId())) {
            UserEntity newUser = userRepository.findById(projectDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + projectDTO.getUserId()));
            existingProject.setUser(newUser);
        }

        Project updatedProject = projectRepository.save(existingProject);
        return convertToDTO(updatedProject);
    }

    @Transactional
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Project not found with id: " + id);
        }
        projectRepository.deleteById(id);
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setUserId(project.getUser().getId());
        dto.setActive(project.getActive());
        dto.setCreatedAt(project.getCreatedAt());
        dto.setUpdatedAt(project.getUpdatedAt());
        return dto;
    }

    private Project convertToEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setActive(dto.getActive() != null ? dto.getActive() : true);
        return project;
    }
}
