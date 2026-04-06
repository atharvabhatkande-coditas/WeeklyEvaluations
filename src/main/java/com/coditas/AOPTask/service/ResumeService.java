package com.coditas.AOPTask.service;

import com.coditas.AOPTask.DTO.ErrorResponse;
import com.coditas.AOPTask.entity.Resume;
import com.coditas.AOPTask.exception.ValidationException;
import com.coditas.AOPTask.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;
    public static final String emailRegx = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public Resume uploadResume(Resume resume) {
        if(!resume.getEmail().matches(emailRegx)){
            throw new ValidationException("Email not valid");
        }
        resumeRepository.save(resume);
        return resume;
    }
}
