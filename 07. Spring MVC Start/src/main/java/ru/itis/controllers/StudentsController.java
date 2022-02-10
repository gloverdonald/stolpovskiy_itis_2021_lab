package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import ru.itis.models.Student;
import ru.itis.services.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class StudentsController implements Controller {
    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (request.getParameter("firstName") != null) {
            Student student = null;
            student = Student.builder()
                    .id(null)
                    .firstName(request.getParameter("firstName"))
                    .lastName(request.getParameter("lastName"))
                    .groupName(request.getParameter("groupName"))
                    .dateOfBirth(Date.valueOf(request.getParameter("dateOfBirth")))
                    .build();
            studentService.saveStudent(student);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("students", studentService.getStudents());
        modelAndView.setViewName("students");
        return modelAndView;
    }
}
