package ru.dias.springdatainterview.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dias.springdatainterview.exceptions.StudentNotFoundException;
import ru.dias.springdatainterview.persistence.dto.StudentDto;
import ru.dias.springdatainterview.persistence.entities.Student;
import ru.dias.springdatainterview.persistence.repositories.StudentRepository;
import ru.dias.springdatainterview.services.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student addStudent(StudentDto studentDto) {
        Student student = Student.builder()
                .name(studentDto.getName())
                .age(studentDto.getAge())
                .build();
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(UUID id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student with id = " + id + " not found");
        }
        return student.get();
    }

    @Override
    public List<Student> getAllStudentsList() {
        return (List<Student>) studentRepository.findAll();
    }
}
