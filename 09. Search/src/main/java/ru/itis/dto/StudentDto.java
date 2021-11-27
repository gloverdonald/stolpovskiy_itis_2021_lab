package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Student;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String groupName;
    private Integer age;

    public static StudentDto from(Student student) {
        return StudentDto.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .id(student.getId())
                .age(student.getAge())
                .groupName(student.getGroupName())
                .build();
    }

    public static List<StudentDto> from(List<Student> accounts) {
        return accounts.stream().map(StudentDto::from).collect(Collectors.toList());
    }
}
