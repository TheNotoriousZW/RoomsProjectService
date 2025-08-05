package com.projservice.projectservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("issues")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    private String id;
    private String projectId;
    private String name;
    private String description;
    private IssueStatus issueStatus;
    private String createdBy;
    private String issuedBy;
    private String updatedAt;
    private Priority priority;
}
