package com.projservice.projectservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.projservice.projectservice.model.Branch;
import com.projservice.projectservice.model.Project;
import com.projservice.projectservice.service.ProjectService;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @QueryMapping
    public Project getProjectById(@Argument String id){
        Project project = projectService.getProjectById(id);
        if(project == null){
            return null;
        }
        return project;
    }

    @QueryMapping
    public List<Project> getAllProjects(){
        List<Project> projects = projectService.getAllProjects();
        return projects;
    }

    @MutationMapping
    public Project createProject(@Argument Project project){
        Project createdProject = projectService.createProject(project);
        return createdProject;
    }

    @MutationMapping
    public Project updateProject(@Argument String id, @Argument Project project){
        Project updatedProject = projectService.updateProject(id, project);
        return updatedProject;
    }

    @MutationMapping
    public Branch updateBranch(@Argument String id, @Argument Branch branch, @Argument String projectId){
        Branch updatedBranch = projectService.updateBranch(id, branch);
        return updatedBranch;
    }

    @MutationMapping
    public Project deleteProject(@Argument String id){
        projectService.deleteProject(id);
        return null;
    }

    @MutationMapping
    public Branch createBranch(@Argument String projectId, @Argument Branch branch){
        Branch createdBranch = projectService.createBranch(projectId, branch);
        return createdBranch;
    }

    @QueryMapping
    public Branch getBranchById(@Argument String id){
        Branch branch = projectService.getBranchById(id);
        if(branch == null){
            return null;
        }
        return branch;
    }

    @QueryMapping
    public List<Branch> getAllBranches(){
        List<Branch> branches = projectService.getAllBranches();
        return branches;
    }
}
