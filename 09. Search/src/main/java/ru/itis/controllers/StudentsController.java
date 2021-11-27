package ru.itis.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.itis.models.Student;
import ru.itis.services.StudentService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/students")
public class StudentsController {
    private final StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public String getStudentsPage(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }
}
