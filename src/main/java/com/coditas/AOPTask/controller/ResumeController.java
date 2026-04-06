package com.coditas.AOPTask.controller;

import com.coditas.AOPTask.DTO.ApplicationResponse;
import com.coditas.AOPTask.entity.Resume;
import com.coditas.AOPTask.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @PostMapping("/resume")
    public ResponseEntity<ApplicationResponse<Resume>>uploadResume(@RequestBody Resume resume){

        ApplicationResponse<Resume>applicationResponse=new ApplicationResponse<>(resumeService.uploadResume(resume));
        return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
    }
}
