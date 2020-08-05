package ru.dias.springdatainterview.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.dias.springdatainterview.persistence.dto.StudentDto;
import ru.dias.springdatainterview.persistence.entities.Student;
import ru.dias.springdatainterview.services.StudentService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/students")
@Transactional
@RequiredArgsConstructor
public class StudentsController {

    private final StudentService studentService;

    @RequestMapping("/list")
    public String showStudentsList(Model model) {
        List<Student> allStudents = studentService.getAllStudentsList();
        model.addAttribute("studentsList", allStudents);
        return "students-list";
    }

    @RequestMapping(path="/add", method= RequestMethod.GET)
    public String showAddForm(Model model) {
        StudentDto studentDto = StudentDto.builder()
                .name("Unknown")
                .age(18)
                .build();
        model.addAttribute("student", studentDto);
        return "add-student-form";
    }

    @RequestMapping(path="/add", method=RequestMethod.POST)
    public String showAddForm(StudentDto studentDto) {
        studentService.addStudent(studentDto);
        return "redirect:/students/list";
    }

    @RequestMapping(path="/remove/{id}", method=RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") UUID studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students/list";
    }

    @RequestMapping(path = "update/{id}", method = RequestMethod.GET)
    public String updateById(@PathVariable(value = "id") UUID studentId,
                             Model model) {
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        return "update-student-form";
    }

    @RequestMapping(path = "update", method = RequestMethod.POST)
    public String updateById(Student student) {
        studentService.updateStudent(student);
        return "redirect:/students/list";
    }
}
