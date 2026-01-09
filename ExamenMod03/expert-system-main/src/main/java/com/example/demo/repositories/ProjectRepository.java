package com.example.demo.repositories;

import com.example.demo.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByUserId(Long userId);

    List<Project> findByUserIdAndActive(Long userId, Boolean active);
}
