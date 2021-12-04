package ru.itis.services;

import ru.itis.dto.StudentDto;
import ru.itis.models.Student;

import java.util.List;

public interface StudentService {
    List<StudentDto> getStudents();

    List<StudentDto> saveStudent(StudentDto student);
}
