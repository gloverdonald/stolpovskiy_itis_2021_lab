package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.StudentDto;
import ru.itis.repositories.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentDto> getStudentByFirstOrLastName(String firstOrLastName) {
        return studentRepository.findByFirstOrLastName(firstOrLastName);
    }
}
