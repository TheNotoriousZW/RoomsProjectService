package com.projservice.projectservice.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    @Id
    private String id;
    private String projectId;
    private String name;
    private String description;
    private Status status;
    private String createdBy;
    private String deadline;
}
