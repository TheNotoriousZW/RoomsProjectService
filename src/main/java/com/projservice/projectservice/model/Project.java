package com.projservice.projectservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    
    @Id
    private String id;
    private String name;
    private String description;
    private String date;
    private String deadline;
    private List<String> principals;
    private Status status;
    private int progress;
    private String createdBy;
    private String updatedAt;
    private List<Branch> branches;
    private List<Issue> issues;
    private Priority priority;
    private List<String> resources;
    private String teamId;
}
