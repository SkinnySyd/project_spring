package com.rabat.testProject.Repository;

import com.rabat.testProject.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project ,Long> {
}
