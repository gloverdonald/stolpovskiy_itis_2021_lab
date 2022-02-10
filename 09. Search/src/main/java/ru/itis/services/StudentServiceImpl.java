package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dto.StudentDto;
import ru.itis.models.Student;
import ru.itis.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getStudents() {
        return StudentDto.from(studentRepository.findAll());
    }

    @Override
    public List<StudentDto> saveStudent(StudentDto student) {
        Student newStudent = Student.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .groupName(student.getGroupName())
                .age(student.getAge())
                .build();
        studentRepository.save(newStudent);
        return StudentDto.from(studentRepository.findAll());
    }
}
