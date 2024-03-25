package com.exam.services;

import com.exam.dtos.SubjectDto;
import com.exam.entities.Student;
import com.exam.entities.Subject;
import com.exam.repositories.StudentRepository;
import com.exam.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    // POST - Create Subject
    public Subject createSubject(SubjectDto subjectDto) {

        Subject subject = new Subject();
        subject.setSubject_name(subjectDto.getSubject_name());
        Subject saved = subjectRepository.save(subject);
        return saved;
    }

    // PUT - Register Student for Subject
    public String registerStudentForSubject(Integer student_id, Integer subject_id){
        Student student = studentRepository.findById(student_id).orElseThrow(() -> new RuntimeException("Student not found!"));
        Subject subject = subjectRepository.findById(subject_id).orElseThrow(() -> new RuntimeException("Subject not Found!"));

        subject.setStudent(student);
        subjectRepository.save(subject);
        return student.getStudent_name() + " has registered for: " + subject.getSubject_name();
    }
}
