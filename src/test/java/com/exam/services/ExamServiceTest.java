package com.exam.services;

import com.exam.dtos.ExamDto;
import com.exam.dtos.StudentDto;
import com.exam.dtos.SubjectDto;
import com.exam.entities.Exam;
import com.exam.entities.Student;
import com.exam.entities.Subject;
import com.exam.repositories.ExamRepository;
import com.exam.repositories.StudentRepository;
import com.exam.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamService examService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ExamRepository examRepository;

    private Exam exam_saved;

    private Subject subject_saved;

    private Student student_saved;

    @Test
    void createExam() {
        StudentDto studentDto = new StudentDto();
        studentDto.setStudent_name("Name Surname");
        student_saved = studentService.createStudent(studentDto);

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubject_name("Subject");
        subject_saved = subjectService.createSubject(subjectDto);

        subjectService.registerStudentForSubject(student_saved.getStudent_id(), subject_saved.getSubject_id());

        ExamDto examDto = new ExamDto();
        examDto.setSubject_name(subject_saved.getSubject_name());
        examDto.setStudent_id(student_saved.getStudent_id());

        exam_saved = examService.createExam(examDto);
        assertEquals(exam_saved.getExam_name(), exam_saved.getSubject().getSubject_name() + exam_saved.getStudent().getStudent_id());
    }

    @Test
    void getAllExams() {
        List<Exam> allExams = examService.getAllExams();
        assertEquals(allExams.size(), allExams.size());
    }
}