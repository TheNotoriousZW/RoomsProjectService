package com.projservice.projectservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.projservice.projectservice.model.Project;

@Repository
public interface ProjectRepo extends MongoRepository<Project, String> {

}
