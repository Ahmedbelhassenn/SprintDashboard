package com.example.sprintdash.Repositories;

import com.example.sprintdash.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface ProjectRepo extends JpaRepository<Project,Long> {
    Optional<Project> findByProjectId(String projectId);
}
