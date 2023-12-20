package ru.tsipino.rsreuwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tsipino.rsreuwebapp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  Student findFirstById(Integer id);
}
