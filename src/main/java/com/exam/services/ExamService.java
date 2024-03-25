package com.exam.services;

import com.exam.dtos.ExamDto;
import com.exam.entities.Exam;
import com.exam.entities.Student;
import com.exam.entities.Subject;
import com.exam.repositories.ExamRepository;
import com.exam.repositories.StudentRepository;
import com.exam.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    // POST - Create Exam using Student and Subject
    public Exam createExam(ExamDto examDto) throws RuntimeException {
        Exam save = new Exam();
        Exam exam = new Exam();
        Integer studentId = examDto.getStudent_id();
        String subjectToAppear = examDto.getSubject_name();

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student Not Found!"));
        List<Subject> subjectList = student.getSubjectList();
        for(Subject subject : subjectList) {
            if(subject.getSubject_name().equals(subjectToAppear)){
                exam.setExam_name(subject.getSubject_name() + student.getStudent_id());
                exam.setSubject(subject);
                exam.setStudent(student);
                save = examRepository.save(exam);
                return save;
            }
        }
        throw new RuntimeException("Subject Not Registed!");
    }

    // GET - List<Exam>
    public List<Exam> getAllExams() {
        List<Exam> all = examRepository.findAll();
        return all;
    }
}
