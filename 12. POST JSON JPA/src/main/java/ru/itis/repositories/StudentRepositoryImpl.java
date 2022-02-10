package ru.itis.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("select student from Student student", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByFirstOrLastName(String title) {
        TypedQuery<Student> query = entityManager.createQuery("select student from Student student where student.firstName=: title or student.lastName =: title", Student.class);
        query.setParameter("title", title);
        return query.getResultList();
    }
}
