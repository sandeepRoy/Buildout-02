package com.exam.services;

import com.exam.dtos.StudentDto;
import com.exam.entities.Student;
import com.exam.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    // GET - Student by student_id
    public Student getStudent(Integer student_id) {
        Student student = studentRepository.findById(student_id).orElseThrow(() -> new RuntimeException("Student not found!"));
        return student;
    }

    // POST - Create Student
    public Student createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setStudent_name(studentDto.getStudent_name());
        Student saved = studentRepository.save(student);
        return saved;
    }


}
