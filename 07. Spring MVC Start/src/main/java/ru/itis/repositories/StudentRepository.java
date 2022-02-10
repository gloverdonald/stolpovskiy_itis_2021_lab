package ru.itis.repositories;

import ru.itis.models.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();

    void save(Student student);
}
