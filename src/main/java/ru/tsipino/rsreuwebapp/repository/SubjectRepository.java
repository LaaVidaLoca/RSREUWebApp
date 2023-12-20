package ru.tsipino.rsreuwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tsipino.rsreuwebapp.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
  Subject findFirstById(Integer id);
}
