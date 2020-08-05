package ru.dias.springdatainterview.services;

import ru.dias.springdatainterview.persistence.dto.StudentDto;
import ru.dias.springdatainterview.persistence.entities.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {

    Student addStudent(Student student);

    Student addStudent(StudentDto studentDto);

    void deleteStudent(UUID id);

    Student updateStudent(Student student);

    Student getStudentById(UUID id);

    List<Student> getAllStudentsList();

}
