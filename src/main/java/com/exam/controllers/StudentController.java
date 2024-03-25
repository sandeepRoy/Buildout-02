package com.exam.controllers;

import com.exam.dtos.ExamDto;
import com.exam.dtos.StudentDto;
import com.exam.entities.Exam;
import com.exam.entities.Student;
import com.exam.services.ExamService;
import com.exam.services.StudentService;
import com.exam.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamService examService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/{student_id}")
    public ResponseEntity<Student> getAllStudents(@PathVariable Integer student_id) {
        Student student = studentService.getStudent(student_id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody StudentDto studentDto) {
        Student student = studentService.createStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/subjectRegistration/{student_id}/{subject_id}")
    public ResponseEntity<String> getSubject(@PathVariable Integer student_id, @PathVariable Integer subject_id) {
        String registrationResponse = subjectService.registerStudentForSubject(student_id, subject_id);
        return new ResponseEntity<>(registrationResponse, HttpStatus.CREATED);
    }

    @PostMapping("/examRegistration")
    public ResponseEntity<Exam> registerForExam(@RequestBody ExamDto examDto) {
        Exam exam = examService.createExam(examDto);
        return new ResponseEntity<>(exam, HttpStatus.CREATED);
    }
}
