package ru.itis.services;

import ru.itis.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    void saveStudent(Student student);
}
