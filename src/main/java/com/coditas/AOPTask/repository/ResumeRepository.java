package com.coditas.AOPTask.repository;

import com.coditas.AOPTask.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume,Long> {
}
