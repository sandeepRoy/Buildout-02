package com.exam.controllers;

import com.exam.dtos.SubjectDto;
import com.exam.entities.Exam;
import com.exam.entities.Subject;
import com.exam.services.ExamService;
import com.exam.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ExamService examService;

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/createSubject")
    public ResponseEntity<Subject> createSubject(@RequestBody SubjectDto subjectDto) {
        Subject subject = subjectService.createSubject(subjectDto);
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

    @PostMapping("/getAllExams")
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> allExams = examService.getAllExams();
        return new ResponseEntity<>(allExams, HttpStatus.OK);
    }
}
