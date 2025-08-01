package com.projservice.projectservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projservice.projectservice.model.Branch;
import com.projservice.projectservice.model.Project;
import com.projservice.projectservice.repo.ProjectRepo; 

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;


    public Project createProject(Project project){
        return projectRepo.save(project);
    }

    public Project getProjectById(String id){
        return projectRepo.findById(id).orElse(null);
    }

    public List<Project> getAllProjects(){
        return projectRepo.findAll();
    }

    public Branch createBranch(String projectId, Branch branch){
        Project project = getProjectById(projectId);
        if(project == null){
            throw new RuntimeException("Project not found");
        }
        branch.setProjectId(projectId);
        project.getBranches().add(branch);
        projectRepo.save(project);
        return branch;
    }

    public Branch getBranchById(String id){
        return projectRepo.findAll().stream()
            .flatMap(project -> project.getBranches().stream())
            .filter(branch -> branch.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    public List<Branch> getAllBranches(){
        return projectRepo.findAll().stream()
            .flatMap(project -> project.getBranches().stream())
            .collect(Collectors.toList());
    }

    public Project updateProject(String id, Project project){
        Project existingProject = getProjectById(id);
        if(existingProject == null){
            throw new RuntimeException("Project not found");
        }
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setDeadline(project.getDeadline());
        existingProject.setStatus(project.getStatus());
        existingProject.setProgress(project.getProgress());
        existingProject.setUpdatedAt(project.getUpdatedAt());
        return projectRepo.save(existingProject);
    }

    public Branch updateBranch(String id, Branch branch){  
        Branch existingBranch = getBranchById(id);
        if(existingBranch == null){
            throw new RuntimeException("Branch not found");
        }


        existingBranch.setName(branch.getName());
        existingBranch.setDescription(branch.getDescription());
        existingBranch.setDeadline(branch.getDeadline());
        existingBranch.setStatus(branch.getStatus());

        Project project = getProjectById(branch.getProjectId());
        if(project == null){
            throw new RuntimeException("Project not found");
        }
        project.getBranches().remove(existingBranch);
        project.getBranches().add(branch);
        projectRepo.save(project);
        return branch;
    }

    public void deleteProject(String id){
        Project existingProject = getProjectById(id);
        if(existingProject == null){
            throw new RuntimeException("Project not found");
        }
        projectRepo.findById(id).ifPresent(project -> {
            project.getBranches().forEach(branch -> deleteBranch(branch.getId()));
            projectRepo.delete(project);
        });
    }

    public void deleteBranch(String id){
        Branch existingBranch = getBranchById(id);
        if(existingBranch == null){
            throw new RuntimeException("Branch not found");
        }
        projectRepo.findById(existingBranch.getProjectId()).ifPresent(project -> {
            project.getBranches().remove(existingBranch);
            projectRepo.save(project);
        });
    }
}
