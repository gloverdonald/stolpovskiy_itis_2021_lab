package ru.itis.services;

import ru.itis.dto.StudentDto;

import java.util.List;

public interface SearchService {
    List<StudentDto> getStudentByFirstOrLastName(String firstOrLastName);
}
