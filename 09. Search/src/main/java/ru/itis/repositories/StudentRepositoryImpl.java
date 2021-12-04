package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.dto.StudentDto;
import ru.itis.models.Student;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.itis.dto.StudentDto.from;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from student order by id";

    //language=SQL
    private static final String SQL_INSERT = "insert into student(first_name, last_name, group_name, age) " +
            "values (:firstName, :lastName, :groupName,:age)";

    //language=SQL
    private static final String SQL_SELECT_BY_NAME = "select * from student where lower(first_name) ~ lower(:firstOrLastName) or lower(last_name) ~ lower(:firstOrLastName);";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public StudentRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final RowMapper<Student> studentRowMapper = (row, rowNumber) -> Student.builder()
            .id(row.getInt("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .groupName(row.getString("group_name"))
            .age(row.getInt("age"))
            .build();

    @Override
    public List<Student> findAll() {
        return namedParameterJdbcTemplate.query(SQL_SELECT_ALL, studentRowMapper);
    }

    @Override
    public void save(Student student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Map<String, Object> values = new HashMap<>();

        values.put("firstName", student.getFirstName());
        values.put("lastName", student.getLastName());
        values.put("groupName", student.getGroupName());
        values.put("age", student.getAge());
        SqlParameterSource parameterSource = new MapSqlParameterSource(values);

        namedParameterJdbcTemplate.update(SQL_INSERT, parameterSource, keyHolder, new String[]{"id"});

        student.setId(keyHolder.getKeyAs(Integer.class));
    }

    @Override
    public List<StudentDto> findByFirstOrLastName(String firstOrLastName) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("firstOrLastName", firstOrLastName);
        List<Student> students = namedParameterJdbcTemplate.query(SQL_SELECT_BY_NAME, parameterSource, studentRowMapper);
        return from(students);
    }
}
