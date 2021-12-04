package ru.itis.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.dto.StudentDto;
import ru.itis.services.StudentService;

import java.util.List;


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

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StudentDto> addStudent(@RequestBody StudentDto student) {
        return studentService.saveStudent(student);
    }
}
